package one.reevdev.carserve.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.carserve.core.domain.feature.auth.usecase.AuthUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> by lazy { _uiState }

    fun logout() {
        viewModelScope.launch {
            authUseCase.logout()
                .collect {
                    _uiState.update {
                        it.copy(hasLoggedOut = true)
                    }
                }
        }
    }
}

data class MainUiState(
    val hasLoggedOut: Boolean = false
)