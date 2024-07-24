package one.reevdev.carserve.core.domain.feature.profile.usecase

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.domain.feature.profile.model.ProfileParam
import one.reevdev.carserve.core.domain.feature.profile.model.SavedProfile

interface ProfileUseCase {

    fun saveLastProfileData(param: ProfileParam) : Flow<Result<Boolean>>

    fun getLastProfileData() : Flow<Result<SavedProfile>>
}