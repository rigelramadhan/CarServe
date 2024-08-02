package one.reevdev.carserve.core.data.feature.vehicle.datasource.remote

import one.reevdev.carserve.core.data.remote.sheet.SheetsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleRemoteDataSource @Inject constructor(
    private val sheetsApi: SheetsApi
) {

    suspend fun getVehicles() = sheetsApi.getVehicles()
}