package one.reevdev.carserve.core.domain.usecase.service

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.domain.model.Result
import one.reevdev.carserve.core.domain.model.service.ServiceAnalysis
import one.reevdev.carserve.core.domain.model.service.ServiceParam

interface ServiceUseCase {
    fun analyzeService(param: ServiceParam): Flow<Result<ServiceAnalysis>>

    fun getServiceHistory(): Flow<Result<List<ServiceAnalysis>>>
}