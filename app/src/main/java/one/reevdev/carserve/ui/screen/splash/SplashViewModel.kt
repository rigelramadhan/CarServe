package one.reevdev.carserve.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.carserve.core.common.data.handleResource
import one.reevdev.carserve.core.domain.feature.auth.usecase.AuthUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> by lazy { _uiState }

    fun checkLoggedInUser() {
        viewModelScope.launch {
            authUseCase.checkLoggedInUser()
                .collect {
                    it.handleResource(
                        onLoading = { _uiState.value },
                        onFailure = { _, _ -> _uiState.value },
                        onSuccess = { email ->
                            _uiState.update { state ->
                                val postDestination =
                                    if (email.isBlank()) PostSplashDestination.AUTH
                                    else PostSplashDestination.HOME

                                state.copy(
                                    postDestination = postDestination
                                )
                            }
                        }
                    )
                }
        }
    }
}

data class SplashUiState(
    val postDestination: PostSplashDestination = PostSplashDestination.UNDEFINED
)

enum class PostSplashDestination {
    UNDEFINED, HOME, AUTH
}