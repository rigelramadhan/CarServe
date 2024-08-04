package one.reevdev.carserve.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.core.common.data.handleResource
import one.reevdev.carserve.core.domain.feature.profile.usecase.ProfileUseCase
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.core.domain.feature.service.usecase.ServiceUseCase
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerWithVehicle
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val serviceUseCase: ServiceUseCase,
    private val profileUseCase: ProfileUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> by lazy { _uiState }

    init {
        initGreeting()
    }

    fun getRecentCustomers() {
        viewModelScope.launch {
            serviceUseCase.getRecentCustomerWithVehicle()
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
                                    recentCustomers = it.distinct().take(5)
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

    fun initGreeting() {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val greeting = when (hourOfDay) {
            in 5..11 -> "Good morning"
            in 12..16 -> "Good afternoon"
            in 17..20 -> "Good evening"
            else -> "Good night"
        }

        viewModelScope.launch {
            profileUseCase.getServiceAdvisorData()
                .collect { data ->
                    _uiState.update { state ->
                        if (data is Result.Success) {
                            val saName = data.data.substringBefore("@")
                                .replaceFirstChar {
                                    if (it.isLowerCase())
                                        it.titlecase(locale = Locale.getDefault())
                                    else
                                        it.toString()
                                }
                            state.copy(
                                greeting = "$greeting, ${saName}!"
                            )
                        } else {
                            state.copy(
                                greeting = "$greeting!"
                            )
                        }
                    }
                }
        }
    }
}

data class HomeUiState(
    val loadingState: LoadingState = LoadingState.NotLoading,
    val greeting: String = emptyString(),
    val errorMessage: String? = null,
    val analysisHistory: List<ServiceAnalysis> = emptyList(),
    val recentCustomers: List<CustomerWithVehicle> = emptyList(),
)