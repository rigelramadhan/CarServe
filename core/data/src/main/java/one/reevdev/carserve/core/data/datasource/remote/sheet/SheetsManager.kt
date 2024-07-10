package one.reevdev.carserve.core.data.datasource.remote.sheet

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import one.reevdev.carserve.core.data.R
import one.reevdev.carserve.core.data.utils.resourceprovider.ResourceProvider
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.util.Collections
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SheetsManager @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    companion object {
        private const val APPLICATION_NAME = "CarServe"
        private const val TOKENS_DIRECTORY_PATH = "tokens"
        private const val SPREADSHEET_ID = "" // TODO: Put valid Spreadsheet ID
        private const val SERVICES_DATA_RANGE = "" // TODO: Put valid range

        private val JSON_FACTORY: GsonFactory = GsonFactory.getDefaultInstance()
        private val SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY)
    }

    private val availableServicesCache: MutableList<AvailableService> = mutableListOf()

    @Throws(IOException::class)
    fun getCredentials(httpTransport: NetHttpTransport): Credential {
        val input = resourceProvider.getRawResourceAsStream(R.raw.credentials)
        val clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(input))
        val flow = GoogleAuthorizationCodeFlow.Builder(
            httpTransport,
            JSON_FACTORY,
            clientSecrets,
            SCOPES
        ).setDataStoreFactory(FileDataStoreFactory(File(TOKENS_DIRECTORY_PATH)))
            .setAccessType("offline")
            .build()

        val receiver = LocalServerReceiver.Builder().setPort(8888).build()
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
    }

    private fun getServiceList(): List<AvailableService> {
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
        val service = Sheets.Builder(
            httpTransport,
            JSON_FACTORY,
            getCredentials(httpTransport)
        )
            .setApplicationName(APPLICATION_NAME)
            .build()
        val response = service.spreadsheets().values()
            .get(SPREADSHEET_ID, SERVICES_DATA_RANGE)
            .execute()
        val values = response.getValues()
        return values.map { row ->
            AvailableService(
                service = row[1].toString(),
                estimatedPrice = (row[2] as? Double) ?: 0.0,
            )
        }
    }

    fun getAvailableService(): List<AvailableService> {
        if (availableServicesCache.isEmpty()) {
            val data = getServiceList()
            availableServicesCache.addAll(data)
        }
        return availableServicesCache
    }
}