package one.reevdev.carserve.core.data.repository.service

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.datasource.model.service.ServiceAnalysisResult
import one.reevdev.carserve.core.data.datasource.model.service.ServiceParamData

interface ServiceRepository {

    fun analyzeService(param: ServiceParamData): Flow<Result<ServiceAnalysisResult>>

    fun getServiceHistory(): Flow<Result<List<ServiceAnalysisResult>>>
}