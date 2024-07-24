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
import one.reevdev.carserve.core.domain.feature.profile.model.SavedProfile
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.core.domain.feature.service.model.ServiceParam
import one.reevdev.carserve.core.domain.feature.service.usecase.ServiceUseCase
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import one.reevdev.carserve.feature.service.utils.MessageConstants
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
                            loadingState = LoadingState.NotLoading,
                            errorMessage = error.localizedMessage
                        )
                    }
                }
                .collect { result ->
                    _uiState.update { state ->
                        result.handleResource(
                            onLoading = {
                                state.copy(
                                    loadingState = LoadingState.CustomLoading(MessageConstants.ANALYZING_VEHICLE)
                                )
                            },
                            onFailure = { _, errorMessage ->
                                state.copy(
                                    loadingState = LoadingState.NotLoading,
                                    errorMessage = errorMessage
                                )
                            },
                            onSuccess = {
                                state.copy(
                                    loadingState = LoadingState.NotLoading,
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

    fun setLoading(isLoading: LoadingState) {
        _uiState.update {
            it.copy(loadingState = isLoading)
        }
    }

    fun setVehicle(vehicle: Vehicle) {
        _uiState.update {
            it.copy(param = it.param.copy(vehicle = vehicle))
        }
    }

    fun setProfile(profile: SavedProfile) {
        _uiState.update {
            it.copy(param = it.param.copy(profile = profile))
        }
    }
}

data class AnalysisUiState(
    val loadingState: LoadingState = LoadingState.NotLoading,
    val errorMessage: String? = null,
    val serviceAnalysis: ServiceAnalysis = ServiceAnalysis(),
    val param: ServiceParam = ServiceParam()
)