package one.reevdev.carserve.core.data.feature.profile.repository

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.feature.profile.datasource.model.local.LastSavedProfile

interface ProfileRepository {

    fun saveLastProfileData(param: LastSavedProfile) : Flow<Result<Boolean>>

    fun getLastProfileData() : Flow<Result<LastSavedProfile>>
}