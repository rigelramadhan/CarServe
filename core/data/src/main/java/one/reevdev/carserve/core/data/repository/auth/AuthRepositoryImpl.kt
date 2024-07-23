package one.reevdev.carserve.core.data.repository.auth

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.BuildConfig
import one.reevdev.carserve.core.data.datasource.model.auth.LoginParamData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    override fun login(param: LoginParamData): Flow<Result<Boolean>> = flow {
        /** TODO: Implement real auth later
         *  For now, use a testing credential in local.properties
         */
        delay(700)
        param.run {
            if (email == BuildConfig.testingEmail && password == BuildConfig.password)
                emit(Result.Success(true))
            else
                emit(Result.Success(false))
        }
    }
}