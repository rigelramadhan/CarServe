package one.reevdev.carserve.feature.service.screen

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.carserve.core.common.data.handleResource
import one.reevdev.carserve.core.domain.model.service.ServiceAnalysis
import one.reevdev.carserve.core.domain.model.service.ServiceParam
import one.reevdev.carserve.core.domain.model.vehicle.VehicleParam
import one.reevdev.carserve.core.domain.usecase.service.ServiceUseCase
import javax.inject.Inject

@HiltViewModel
class ServiceAnalysisViewModel @Inject constructor(
    private val serviceUseCase: ServiceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnalysisUiState())
    val uiState: StateFlow<AnalysisUiState> by lazy { _uiState }

    fun analyzeVehicle(param: ServiceParam) {
        viewModelScope.launch {
            serviceUseCase.analyzeService(param)
                .catch { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.localizedMessage
                        )
                    }
                }
                .collect { result ->
                    _uiState.update { state ->
                        result.handleResource(
                            onLoading = {
                                state.copy(
                                    isLoading = true
                                )
                            },
                            onFailure = { _, errorMessage ->
                                state.copy(
                                    isLoading = false,
                                    errorMessage = errorMessage
                                )
                            },
                            onSuccess = {
                                state.copy(
                                    isLoading = false,
                                    serviceAnalysis = it
                                )
                            }
                        )
                    }
                }
        }
    }

    fun setSymptoms(symptoms: String) {
        _uiState.update {
            it.copy(param = it.param.copy(symptoms = symptoms))
        }
    }

    fun setGeneralProblem(generalProblem: String) {
        _uiState.update {
            it.copy(param = it.param.copy(generalProblem = generalProblem))
        }
    }

    fun setPhoto(photo: Bitmap?) {
        _uiState.update {
            it.copy(param = it.param.copy(photo = photo))
        }
    }

    fun setLoading(isLoading: Boolean) {
        _uiState.update {
            it.copy(isLoading = isLoading)
        }
    }

    fun setVehicle(vehicle: VehicleParam) {
        _uiState.update {
            it.copy(param = it.param.copy(vehicle = vehicle))
        }
    }
}

data class AnalysisUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val serviceAnalysis: ServiceAnalysis = ServiceAnalysis(),
    val param: ServiceParam = ServiceParam()
)