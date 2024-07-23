package one.reevdev.carserve.core.data.repository.auth

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.datasource.model.auth.LoginParamData

interface AuthRepository {
    fun login(param: LoginParamData): Flow<Result<Boolean>>

    fun checkLoggedInUser(): Flow<Result<String>>
}