package one.reevdev.carserve.core.data.feature.auth.repository

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.feature.auth.datasource.model.LoginParamData

interface AuthRepository {
    fun login(param: LoginParamData): Flow<Result<Boolean>>

    fun checkLoggedInUser(): Flow<Result<String>>

    fun logout(): Flow<Result<Boolean>>
}