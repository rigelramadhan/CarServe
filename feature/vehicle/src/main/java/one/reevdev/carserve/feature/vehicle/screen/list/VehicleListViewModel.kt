package one.reevdev.carserve.feature.vehicle.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.carserve.core.common.data.handleResource
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle
import one.reevdev.carserve.core.domain.feature.vehicle.usecase.VehicleUseCase
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import javax.inject.Inject

@HiltViewModel
class VehicleListViewModel @Inject constructor(
    private val useCase: VehicleUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(VehicleListUiState())
    val uiState: StateFlow<VehicleListUiState> by lazy { _uiState }

    fun getAllVehicle() {
        viewModelScope.launch {
            useCase.getAllCustomerVehicles()
                .catch {  }
                .collect { result ->
                    _uiState.update { state ->
                        result.handleResource(
                            onLoading = {
                                state.copy(
                                    loadingState = LoadingState.DefaultLoading,
                                )
                            },
                            onSuccess = {
                                state.copy(
                                    loadingState = LoadingState.NotLoading,
                                    errorMessage = null,
                                    vehicles = it
                                )
                            },
                            onFailure = { _, message ->
                                state.copy(
                                    loadingState = LoadingState.NotLoading,
                                    errorMessage = message
                                )
                            }
                        )
                    }
                }
        }
    }

    fun deleteVehicle(policeNo: String) {
        viewModelScope.launch {
            useCase.deleteCustomerVehicle(policeNo)
                .catch { }
                .collect {
                    _uiState.update { state ->
                        it.handleResource(
                            onLoading = {
                                state.copy(
                                    loadingState = LoadingState.DefaultLoading,
                                )
                            },
                            onSuccess = {
                                state.copy(
                                    loadingState = LoadingState.NotLoading,
                                    errorMessage = null,
                                )
                            },
                            onFailure = { _, message ->
                                state.copy(
                                    loadingState = LoadingState.NotLoading,
                                    errorMessage = message
                                )
                            }
                        )
                    }
                    getAllVehicle()
                }
        }
    }
}

data class VehicleListUiState(
    val loadingState: LoadingState = LoadingState.NotLoading,
    val errorMessage: String? = null,
    val vehicles: List<CustomerVehicle> = emptyList(),
)