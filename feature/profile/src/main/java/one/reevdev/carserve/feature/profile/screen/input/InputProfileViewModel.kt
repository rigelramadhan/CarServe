package one.reevdev.carserve.feature.profile.screen.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
                    it.handleResource(
                        onLoading = {

                        },
                        onSuccess = {

                        },
                        onFailure = { _, errorMessage ->

                        }
                    )
                }
        }
    }
}

data class InputProfileUiState(
    val loadingState: LoadingState = LoadingState.NotLoading,
    val errorMessage: String? = null,
)