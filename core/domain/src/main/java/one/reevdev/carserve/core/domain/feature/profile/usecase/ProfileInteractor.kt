package one.reevdev.carserve.core.domain.feature.profile.usecase

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.common.data.mapFlowData
import one.reevdev.carserve.core.data.feature.profile.repository.ProfileRepository
import one.reevdev.carserve.core.domain.feature.profile.model.Customer
import one.reevdev.carserve.core.domain.utils.toDomain
import one.reevdev.carserve.core.domain.utils.toRequest
import javax.inject.Inject

class ProfileInteractor @Inject constructor(
    private val profileRepository: ProfileRepository,
) : ProfileUseCase {

    override fun saveCustomer(param: Customer): Flow<Result<Boolean>> {
        return profileRepository.saveCustomer(param.toRequest())
    }

    override fun getAllCustomers(): Flow<Result<List<Customer>>> {
        return profileRepository.getAllCustomers()
            .mapFlowData { result -> result.map { it.toDomain() } }
    }

    override fun getCustomerByEmail(email: String): Flow<Result<Customer>> {
        return profileRepository.getCustomerByEmail(email).mapFlowData {
            it.toDomain()
        }
    }

    override fun getServiceAdvisorData(): Flow<Result<String>> {
        return profileRepository.getServiceAdvisorData()
    }
}