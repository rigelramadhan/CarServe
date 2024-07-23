package one.reevdev.carserve.feature.profile.navigation

import kotlinx.serialization.Serializable

sealed class AuthRoutes {
    @Serializable
    data object Login

    @Serializable
    data object Register
}