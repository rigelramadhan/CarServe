package one.reevdev.carserve.core.data.repository.auth

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.BuildConfig
import one.reevdev.carserve.core.data.datasource.model.auth.LoginParamData
import one.reevdev.carserve.core.data.datastore.AuthPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authPreferences: AuthPreferences
) : AuthRepository {
    override fun login(param: LoginParamData): Flow<Result<Boolean>> = flow {
        /** TODO: Implement real auth later
         *  For now, use a testing credential in local.properties
         */
        delay(700)
        param.run {
            if (email == BuildConfig.testingEmail && password == BuildConfig.password) {
                authPreferences.setUserEmail(email)
                emit(Result.Success(true))
            } else
                emit(Result.Success(false))
        }
    }

    override fun checkLoggedInUser(): Flow<Result<String>> = flow {
        emit(Result.Loading())
        authPreferences.getUserEmail()
            .collect {
                emit(Result.Success(it))
            }
    }

    override fun logout(): Flow<Result<Boolean>> = flow {
        authPreferences.logout()
        emit(Result.Success(true))
    }
}