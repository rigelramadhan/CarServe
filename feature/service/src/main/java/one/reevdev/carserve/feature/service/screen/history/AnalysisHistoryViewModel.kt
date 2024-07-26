package one.reevdev.carserve.feature.service.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.carserve.core.common.data.handleResource
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.core.domain.feature.service.usecase.ServiceUseCase
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import javax.inject.Inject

@HiltViewModel
class AnalysisHistoryViewModel @Inject constructor(
    private val useCase: ServiceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnalysisHistoryUiState())
    val uiState: StateFlow<AnalysisHistoryUiState> by lazy { _uiState }

    fun getAnalysisHistory() {
        viewModelScope.launch {
            useCase.getServiceHistory()
                .catch { throwable ->
                    _uiState.update {
                        it.copy(
                            errorMessage = throwable.localizedMessage
                        )
                    }
                }
                .collect { result ->
                    _uiState.update { state ->
                        result.handleResource(
                            onLoading = {
                                state.copy(
                                    loadingState = LoadingState.DefaultLoading
                                )
                            },
                            onSuccess = {
                                state.copy(
                                    loadingState = LoadingState.NotLoading,
                                    errorMessage = null,
                                    analysisHistory = it
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

data class AnalysisHistoryUiState(
    val loadingState: LoadingState = LoadingState.NotLoading,
    val errorMessage: String? = null,
    val analysisHistory: List<ServiceAnalysis> = emptyList(),
)