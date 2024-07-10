package one.reevdev.carserve.core.data.repository.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.datasource.model.ServiceAnalysisResult
import one.reevdev.carserve.core.data.datasource.model.ServiceParamData
import one.reevdev.carserve.core.data.datasource.remote.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ServiceRepository {

    override fun analyzeService(param: ServiceParamData): Flow<Result<ServiceAnalysisResult>> {
        return remoteDataSource.analyzeService(param)
    }

    override fun getServiceHistory(): Flow<Result<List<ServiceAnalysisResult>>> {
        return flow { emit(Result.Loading()) } // TODO: Implement Later
    }
}