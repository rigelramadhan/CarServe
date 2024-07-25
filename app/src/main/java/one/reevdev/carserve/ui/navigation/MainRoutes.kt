package one.reevdev.carserve.ui.navigation

import kotlinx.serialization.Serializable
import one.reevdev.carserve.feature.common.ui.navigation.Route

sealed class MainRoutes {

    @Serializable
    data object Main : Route

    @Serializable
    data object Home : Route

    @Serializable
    data object Splash : Route
}