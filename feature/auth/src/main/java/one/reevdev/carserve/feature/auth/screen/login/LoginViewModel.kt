package one.reevdev.carserve.feature.auth.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.carserve.core.common.data.handleResource
import one.reevdev.carserve.core.domain.feature.auth.model.LoginParam
import one.reevdev.carserve.core.domain.feature.auth.usecase.AuthUseCase
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> by lazy { _uiState }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authUseCase.login(LoginParam(email, password))
                .catch {
                    _uiState.update { state ->
                        state.copy(
                            loadingState = LoadingState.NotLoading,
                            errorMessage = it.localizedMessage.orEmpty(),
                            loginState = LoginState.Error
                        )
                    }
                }
                .collect {
                    _uiState.update { state ->
                        it.handleResource(
                            onLoading = {
                                state.copy(
                                    loadingState = LoadingState.DefaultLoading,
                                    loginState = LoginState.Default
                                )
                            },
                            onSuccess = {
                                state.copy(
                                    loadingState = LoadingState.NotLoading,
                                    errorMessage = null,
                                    loginState = if (it) LoginState.Success else LoginState.WrongCredential
                                )
                            },
                            onFailure = { _, message ->
                                state.copy(
                                    loadingState = LoadingState.NotLoading,
                                    errorMessage = message,
                                    loginState = LoginState.Error
                                )
                            }
                        )
                    }
                }
        }
    }
}

data class LoginUiState(
    val loadingState: LoadingState = LoadingState.NotLoading,
    val errorMessage: String? = null,
    val loginState: LoginState = LoginState.Default,
)

enum class LoginState {
    Default, Success, WrongCredential, Error
}