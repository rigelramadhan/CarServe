package one.reevdev.carserve.vehicle.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.carserve.core.common.data.handleResource
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.core.domain.feature.vehicle.usecase.VehicleUseCase
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import javax.inject.Inject

@HiltViewModel
class AddVehicleViewModel @Inject constructor(
    private val vehicleUseCase: VehicleUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddVehicleUiState())
    val uiState: StateFlow<AddVehicleUiState> by lazy { _uiState }

    fun saveVehicle(vehicle: Vehicle) {
        viewModelScope.launch {
            vehicleUseCase.saveVehicle(vehicle)
                .catch {  }
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
                                    vehicleSaved = true,
                                    errorMessage = null
                                )
                            },
                            onFailure = { _, message ->
                                state.copy(
                                    loadingState = LoadingState.NotLoading,
                                    errorMessage = message
                                )
                            },
                        )
                    }
                }
        }
    }

    fun getAllVehicle() {
        viewModelScope.launch {
            vehicleUseCase.getAllVehicles()
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
}

data class AddVehicleUiState(
    val loadingState: LoadingState = LoadingState.NotLoading,
    val errorMessage: String? = null,
    val vehicleSaved: Boolean = false,
    val vehicles: List<Vehicle> = emptyList(),
)