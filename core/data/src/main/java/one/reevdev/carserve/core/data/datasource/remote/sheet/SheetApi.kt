package one.reevdev.carserve.core.data.datasource.remote.sheet

import com.github.theapache64.retrosheet.annotations.Read
import one.reevdev.carserve.core.data.datasource.model.AvailableService
import retrofit2.http.GET

interface SheetApi {
    @Read("SELECT *")
    @GET("Services")
    suspend fun getServices(): List<AvailableService>
}
