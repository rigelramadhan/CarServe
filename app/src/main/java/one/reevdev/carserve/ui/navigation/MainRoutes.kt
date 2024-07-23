package one.reevdev.carserve.ui.navigation

import kotlinx.serialization.Serializable

sealed class MainRoutes {

    @Serializable
    data object Main

    @Serializable
    data object Home

    @Serializable
    data object Splash
}