package one.reevdev.carserve.feature.profile.screen.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.carserve.core.common.data.handleResource
import one.reevdev.carserve.core.domain.feature.profile.model.Customer
import one.reevdev.carserve.core.domain.feature.profile.usecase.ProfileUseCase
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import javax.inject.Inject

@HiltViewModel
class InputProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(InputProfileUiState())
    val uiState: StateFlow<InputProfileUiState> by lazy { _uiState }

    fun saveLastProfileData(param: Customer) {
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
                                    param = Customer(
                                        name = data.name,
                                        email = data.email,
                                        phoneNumber = data.phoneNumber,
                                        address = data.address
                                    ),
                                    isPrefilled = data != Customer()
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

    fun setName(name: String) {
        _uiState.update {
            it.copy(param = it.param.copy(name = name))
        }
    }

    fun setEmail(email: String) {
        _uiState.update {
            it.copy(param = it.param.copy(email = email))
        }
    }

    fun setPhoneNumber(phoneNumber: String) {
        _uiState.update {
            it.copy(param = it.param.copy(phoneNumber = phoneNumber))
        }
    }

    fun setAddress(address: String) {
        _uiState.update {
            it.copy(param = it.param.copy(address = address))
        }
    }

    fun removePrefilledData() {
        _uiState.update {
            it.copy(param = Customer(), isPrefilled = false)
        }
    }
}

data class InputProfileUiState(
    val loadingState: LoadingState = LoadingState.NotLoading,
    val errorMessage: String? = null,
    val param: Customer = Customer(),
    val isPrefilled: Boolean = false,
)