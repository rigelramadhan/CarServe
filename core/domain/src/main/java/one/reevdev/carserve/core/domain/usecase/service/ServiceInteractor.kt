package one.reevdev.carserve.core.domain.usecase.service

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.common.data.mapFlowData
import one.reevdev.carserve.core.data.repository.service.ServiceRepository
import one.reevdev.carserve.core.domain.model.service.ServiceAnalysis
import one.reevdev.carserve.core.domain.model.service.ServiceParam
import one.reevdev.carserve.core.domain.utils.toDomain
import one.reevdev.carserve.core.domain.utils.toRequest
import javax.inject.Inject

class ServiceInteractor @Inject constructor(
    private val repository: ServiceRepository
) : ServiceUseCase {

    override fun analyzeService(param: ServiceParam): Flow<Result<ServiceAnalysis>> {
        return repository.analyzeService(param.toRequest()).mapFlowData {
            it.toDomain()
        }
    }

    override fun getServiceHistory(): Flow<Result<List<ServiceAnalysis>>> {
        return repository.getServiceHistory().mapFlowData { data -> data.map { it.toDomain() } }
    }
}