package one.reevdev.carserve.core.domain.feature.profile.usecase

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.common.data.mapFlowData
import one.reevdev.carserve.core.data.repository.profile.ProfileRepository
import one.reevdev.carserve.core.domain.feature.profile.model.ProfileParam
import one.reevdev.carserve.core.domain.feature.profile.model.SavedProfile
import one.reevdev.carserve.core.domain.utils.toDomain
import one.reevdev.carserve.core.domain.utils.toRequest
import javax.inject.Inject

class ProfileInteractor @Inject constructor(
    private val profileRepository: ProfileRepository
) : ProfileUseCase {

    override fun saveLastProfileData(param: ProfileParam): Flow<Result<Boolean>> {
        return profileRepository.saveLastProfileData(param.toRequest())
    }

    override fun getLastProfileData(): Flow<Result<SavedProfile>> {
        return profileRepository.getLastProfileData().mapFlowData {
            it.toDomain()
        }
    }
}