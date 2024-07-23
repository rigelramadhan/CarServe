package one.reevdev.carserve.core.domain.feature.auth.usecase

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.domain.feature.auth.model.LoginParam

interface AuthUseCase {
    fun login(param: LoginParam): Flow<Result<Boolean>>

    fun checkLoggedInUser(): Flow<Result<String>>
}