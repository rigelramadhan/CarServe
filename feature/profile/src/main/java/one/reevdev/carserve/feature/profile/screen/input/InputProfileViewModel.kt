package one.reevdev.carserve.feature.profile.screen.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.carserve.core.common.data.handleResource
import one.reevdev.carserve.core.domain.feature.profile.model.ProfileParam
import one.reevdev.carserve.core.domain.feature.profile.usecase.ProfileUseCase
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import javax.inject.Inject

@HiltViewModel
class InputProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(InputProfileUiState())
    val uiState: StateFlow<InputProfileUiState> by lazy { _uiState }

    fun saveLastProfileData(param: ProfileParam) {
        viewModelScope.launch {
            profileUseCase.saveLastProfileData(param)
                .collect {
                    _uiState.update { state ->
                        it.handleResource(
                            onLoading = {
                                state.copy(
                                    loadingState = LoadingState.DefaultLoading
                                )
                            },
                            onSuccess = {
                                state.copy(
                                    loadingState = LoadingState.NotLoading,
                                    errorMessage = null,
                                )
                            },
                            onFailure = { _, errorMessage ->
                                state.copy(
                                    loadingState = LoadingState.NotLoading,
                                    errorMessage = errorMessage
                                )
                            }
                        )
                    }
                }
        }
    }

    fun getLastProfileData() {
        viewModelScope.launch {
            profileUseCase.getLastProfileData()
                .collect {
                    _uiState.update { state ->
                        it.handleResource(
                            onLoading = {
                                state.copy(
                                    loadingState = LoadingState.DefaultLoading
                                )
                            },
                            onSuccess = { data ->
                                state.copy(
                                    loadingState = LoadingState.NotLoading,
                                    errorMessage = null,
                                    param = ProfileParam(
                                        name = data.name,
                                        email = data.email,
                                        phoneNumber = data.phoneNumber,
                                        address = data.address
                                    )
                                )
                            },
                            onFailure = { _, errorMessage ->
                                state.copy(
                                    loadingState = LoadingState.NotLoading,
                                    errorMessage = errorMessage
                                )
                            }
                        )
                    }
                }
        }
    }
}

data class InputProfileUiState(
    val loadingState: LoadingState = LoadingState.NotLoading,
    val errorMessage: String? = null,
    val param: ProfileParam = ProfileParam()
)