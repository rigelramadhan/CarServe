package one.reevdev.carserve.core.data.repository.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.datasource.model.ServiceAnalysisResult
import one.reevdev.carserve.core.data.datasource.model.ServiceParamData
import one.reevdev.carserve.core.data.datasource.remote.gemini.ServiceGeminiApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceRepositoryImpl @Inject constructor(
    private val geminiApi: ServiceGeminiApi
) : ServiceRepository {

    override fun analyzeService(param: ServiceParamData): Flow<Result<ServiceAnalysisResult>> = flow {
        emit(Result.Loading())
        try {
            val data = geminiApi.analyzeService(param)
            Result.Success(data)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override fun getServiceHistory(): Flow<Result<List<ServiceAnalysisResult>>> {
        return flow { emit(Result.Loading()) } // TODO: Implement Later
    }
}