package one.reevdev.carserve.feature.common.ui.state

import androidx.annotation.StringRes

sealed class LoadingState {
    data class CustomLoading(
        @StringRes val messageRes: Int? = null,
        val message: String? = null
    ) : LoadingState()

    data object DefaultLoading : LoadingState()
    data object NotLoading : LoadingState()
}