package one.reevdev.carserve.feature.profile.screen.list

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
class CustomerListViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CustomerListUiState())
    val uiState: StateFlow<CustomerListUiState> by lazy { _uiState }

    fun getAllCustomers() {
        viewModelScope.launch {
            profileUseCase.getAllCustomers()
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
                                    customers = data
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

data class CustomerListUiState(
    val loadingState: LoadingState = LoadingState.NotLoading,
    val errorMessage: String? = null,
    val customers: List<Customer> = emptyList(),
)