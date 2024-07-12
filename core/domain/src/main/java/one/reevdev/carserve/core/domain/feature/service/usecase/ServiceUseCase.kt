package one.reevdev.carserve.core.domain.feature.service.usecase

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.core.domain.feature.service.model.ServiceParam

interface ServiceUseCase {
    fun analyzeService(param: ServiceParam): Flow<Result<ServiceAnalysis>>

    fun getServiceHistory(): Flow<Result<List<ServiceAnalysis>>>
}