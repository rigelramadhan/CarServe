package one.reevdev.carserve.core.data.feature.service.repository

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceAnalysisResult
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceParamData

interface ServiceRepository {

    fun analyzeService(param: ServiceParamData): Flow<Result<ServiceAnalysisResult>>

    fun getServiceHistory(): Flow<Result<List<ServiceAnalysisResult>>>
}