package one.reevdev.carserve.core.data.feature.service.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.withContext
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.feature.service.datasource.local.ServiceLocalDataSource
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceAnalysisResult
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceParamData
import one.reevdev.carserve.core.data.gemini.GeminiDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceRepositoryImpl @Inject constructor(
    private val geminiDataSource: GeminiDataSource,
    private val localDataSource: ServiceLocalDataSource,
) : ServiceRepository {

    override fun analyzeService(param: ServiceParamData): Flow<Result<ServiceAnalysisResult>> = flow {
        val result = geminiDataSource.analyzeService(param).last()
        if (result is Result.Success) {
            withContext(Dispatchers.IO) {
                localDataSource.insertServiceAnalysis(result.data)
            }
            emit(Result.Success(result.data))
        } else {
            emit(result)
        }
    }

    override fun getServiceHistory(): Flow<Result<List<ServiceAnalysisResult>>> = flow {
        emit(Result.Loading())
        try {
            emit(Result.Success(localDataSource.getAllServiceAnalysis()))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}