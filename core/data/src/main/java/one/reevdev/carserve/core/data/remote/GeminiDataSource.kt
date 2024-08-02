package one.reevdev.carserve.core.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceAnalysisResult
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceParamData
import one.reevdev.carserve.core.data.remote.api.ServiceGeminiApi
import one.reevdev.carserve.core.data.remote.sheet.SheetsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiDataSource @Inject constructor(
    private val geminiApi: ServiceGeminiApi,
    private val sheetsApi: SheetsApi
) {

    fun analyzeService(param: ServiceParamData): Flow<Result<ServiceAnalysisResult>> = flow {
        emit(Result.Loading())
        try {
            val availableServices = sheetsApi.getServices()
            val data = geminiApi.analyzeService(param, availableServices)
            emit(Result.Success(data))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}