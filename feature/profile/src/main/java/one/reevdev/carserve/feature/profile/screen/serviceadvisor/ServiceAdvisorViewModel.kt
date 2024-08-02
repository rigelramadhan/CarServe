package one.reevdev.carserve.feature.profile.screen.serviceadvisor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.core.domain.feature.profile.usecase.ProfileUseCase
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import javax.inject.Inject

@HiltViewModel
class ServiceAdvisorViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ServiceAdvisorUiState())
    val uiState: StateFlow<ServiceAdvisorUiState> by lazy { _uiState }

    fun getServiceAdvisorData() {
        viewModelScope.launch {
            profileUseCase.getServiceAdvisorData()
                .collect {
                    if (it is Result.Success) {
                        _uiState.update { state ->
                            val data = it.data
                            state.copy(
                                loadingState = LoadingState.NotLoading,
                                username = data.substringBefore("@"),
                                email = data
                            )
                        }
                    } else {
                        _uiState.update { state ->
                            state.copy(
                                loadingState = LoadingState.NotLoading,
                            )
                        }
                    }
                }
        }
    }
}

data class ServiceAdvisorUiState(
    val loadingState: LoadingState = LoadingState.NotLoading,
    val username: String = emptyString(),
    val email: String = emptyString(),
)