package one.reevdev.carserve.core.data.datasource.remote.sheet

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl
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
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import one.reevdev.carserve.core.data.R
import one.reevdev.carserve.core.data.utils.resourceprovider.ResourceProvider
import java.io.File
import java.io.InputStreamReader
import java.util.Collections
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SheetsManager @Inject constructor(
    private val resourceProvider: ResourceProvider,
    @ApplicationContext val context: Context,
) {

    companion object {
        private const val APPLICATION_NAME = "CarServe"
        private const val TOKENS_DIRECTORY_PATH = "tokens"
        private const val SPREADSHEET_ID = "1gn_ahsOZJNethl5ADa3cRStt0L-kAxmC0WEDN-q9Pwc"
        private const val SERVICES_DATA_RANGE = "Class Data!B2:C"

        private val JSON_FACTORY: GsonFactory = GsonFactory.getDefaultInstance()
        private val SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY)
    }

    private val availableServicesCache: MutableList<AvailableService> = mutableListOf()

    private suspend fun getCredentials(httpTransport: NetHttpTransport): Flow<Credential?> = channelFlow {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    val input = resourceProvider.getRawResourceAsStream(R.raw.credentials)
                    val clientSecrets =
                        GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(input))
                    val tokensDirectory = File(context.filesDir, TOKENS_DIRECTORY_PATH)
                    if (!tokensDirectory.exists()) {
                        tokensDirectory.mkdirs()
                    }


                    val flow = GoogleAuthorizationCodeFlow.Builder(
                        httpTransport,
                        JSON_FACTORY,
                        clientSecrets,
                        SCOPES
                    ).setDataStoreFactory(FileDataStoreFactory(tokensDirectory))
                        .setAccessType("offline")
                        .build()

                    val receiver = LocalServerReceiver.Builder().setPort(8888).build()
                    val credential = object : AuthorizationCodeInstalledApp(flow, receiver) {
                        override fun onAuthorization(authorizationUrl: AuthorizationCodeRequestUrl?) {
                            val url = authorizationUrl?.build()
                            Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                context.startActivity(this)
                            }
                        }
                    }
                    send(credential.authorize("user"))
                } catch (e: Exception) {
                    Log.e("", e.localizedMessage.orEmpty())
                    send(null)
                }
            }
        }
    }

    private suspend fun getServiceList(): Flow<List<AvailableService>?> = flow {
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
        getCredentials(httpTransport)
            .collect {
                it?.let {
                    val service = Sheets.Builder(
                        httpTransport,
                        JSON_FACTORY,
                        it
                    )
                        .setApplicationName(APPLICATION_NAME)
                        .build()
                    val response = service.spreadsheets().values()
                        .get(SPREADSHEET_ID, SERVICES_DATA_RANGE)
                        .execute()
                    val values = response.getValues()
                    emit(values.map { row ->
                        AvailableService(
                            service = row[1].toString(),
                            estimatedPrice = (row[2] as? Double) ?: 0.0,
                        )
                    })
                } ?: emit(null)
            }
    }

    suspend fun getAvailableService(): Flow<List<AvailableService>?> = flow {
        val data = getServiceList()
        data.collect {
            it?.let {
                if (availableServicesCache.isEmpty()) {
                    availableServicesCache.addAll(it)
                }
                emit(availableServicesCache)
            }
        }
    }
}