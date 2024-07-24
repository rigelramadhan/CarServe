package one.reevdev.carserve.core.data.repository.profile

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.datasource.model.profile.LastSavedProfile
import one.reevdev.carserve.core.data.datasource.model.profile.ProfileParamData

interface ProfileRepository {

    fun saveLastProfileData(param: ProfileParamData) : Flow<Result<Boolean>>

    fun getLastProfileData() : Flow<Result<LastSavedProfile>>
}