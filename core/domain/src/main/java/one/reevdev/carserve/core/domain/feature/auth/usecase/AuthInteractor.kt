package one.reevdev.carserve.core.domain.feature.auth.usecase

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.feature.auth.repository.AuthRepository
import one.reevdev.carserve.core.domain.feature.auth.model.LoginParam
import one.reevdev.carserve.core.domain.utils.toRequest
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val repository: AuthRepository) : AuthUseCase {
    override fun login(param: LoginParam): Flow<Result<Boolean>> {
        return repository.login(param.toRequest())
    }

    override fun checkLoggedInUser(): Flow<Result<String>> {
        return repository.checkLoggedInUser()
    }

    override fun logout(): Flow<Result<Boolean>> {
        return repository.logout()
    }
}