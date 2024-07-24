package one.reevdev.carserve.core.data.repository.profile

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.datasource.model.profile.LastSavedProfile
import one.reevdev.carserve.core.data.datasource.model.profile.ProfileParamData
import one.reevdev.carserve.core.data.datastore.ProfilePreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val profilePreferences: ProfilePreferences
) : ProfileRepository {

    override fun saveLastProfileData(param: ProfileParamData): Flow<Result<Boolean>> = flow {
        profilePreferences.run {
            setLastUserName(param.name)
            setLastUserEmail(param.email)
            setLastUserPhoneNumber(param.phoneNumber)
            setLastUserAddress(param.address)
        }
        emit(Result.Success(true))
    }

    override fun getLastProfileData(): Flow<Result<LastSavedProfile>> = flow {
        profilePreferences.run {
            combine(
                getLastUserName(),
                getLastUserEmail(),
                getLastUserPhoneNumber(),
                getLastUserAddress()
            ) { name, email, phoneNumber, address ->
                LastSavedProfile(name, email, phoneNumber, address)
            }.collect {
                emit(Result.Success(it))
            }
        }
    }
}