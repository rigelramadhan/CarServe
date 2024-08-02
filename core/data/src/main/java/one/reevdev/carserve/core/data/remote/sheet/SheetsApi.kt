package one.reevdev.carserve.core.data.remote.sheet

import com.github.theapache64.retrosheet.annotations.Read
import one.reevdev.carserve.core.data.feature.service.datasource.model.AvailableService
import one.reevdev.carserve.core.data.feature.vehicle.datasource.remote.model.VehicleResponse
import retrofit2.http.GET

interface SheetsApi {
    @Read("SELECT *")
    @GET("Services")
    suspend fun getServices(): List<AvailableService>

    @Read("SELECT *")
    @GET("Vehicle")
    suspend fun getVehicles(): List<VehicleResponse>
}
