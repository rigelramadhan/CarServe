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
    private val profileRepository: ProfileRepository
) : ProfileUseCase {

    override fun saveLastProfileData(param: Customer): Flow<Result<Boolean>> {
        return profileRepository.saveCustomer(param.toRequest())
    }

    override fun getLastProfileData(): Flow<Result<Customer>> {
        return profileRepository.getLastCustomer().mapFlowData {
            it.toDomain()
        }
    }

    override fun getServiceAdvisorData(): Flow<Result<String>> {
        return profileRepository.getServiceAdvisorData()
    }
}