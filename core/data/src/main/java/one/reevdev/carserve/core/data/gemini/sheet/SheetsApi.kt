package one.reevdev.carserve.core.data.gemini.sheet

import com.github.theapache64.retrosheet.annotations.Read
import one.reevdev.carserve.core.data.feature.service.datasource.model.AvailableService
import retrofit2.http.GET

interface SheetsApi {
    @Read("SELECT *")
    @GET("Services")
    suspend fun getServices(): List<AvailableService>
}
