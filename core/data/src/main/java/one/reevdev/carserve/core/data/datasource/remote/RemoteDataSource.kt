package one.reevdev.carserve.core.data.datasource.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.datasource.model.ServiceAnalysisResult
import one.reevdev.carserve.core.data.datasource.model.ServiceParamData
import one.reevdev.carserve.core.data.datasource.remote.gemini.ServiceGeminiApi
import one.reevdev.carserve.core.data.datasource.remote.sheet.AvailableService
import one.reevdev.carserve.core.data.datasource.remote.sheet.SheetsManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val geminiApi: ServiceGeminiApi,
    private val sheetsManager: SheetsManager
) {
    private fun getServicesList(): List<AvailableService> = sheetsManager.getAvailableService()

    fun analyzeService(param: ServiceParamData): Flow<Result<ServiceAnalysisResult>> = flow {
        emit(Result.Loading())
        try {
            val data = geminiApi.analyzeService(param, getServicesList())
            emit(Result.Success(data))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}