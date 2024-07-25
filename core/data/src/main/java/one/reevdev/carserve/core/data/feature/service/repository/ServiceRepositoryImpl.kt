package one.reevdev.carserve.core.data.feature.service.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceAnalysisResult
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceParamData
import one.reevdev.carserve.core.data.gemini.GeminiDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceRepositoryImpl @Inject constructor(
    private val geminiDataSource: GeminiDataSource
) : ServiceRepository {

    override fun analyzeService(param: ServiceParamData): Flow<Result<ServiceAnalysisResult>> =
        geminiDataSource.analyzeService(param)

    override fun getServiceHistory(): Flow<Result<List<ServiceAnalysisResult>>> {
        return flow { emit(Result.Loading()) } // TODO: Implement Later
    }
}