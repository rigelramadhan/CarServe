package one.reevdev.carserve.feature.profile.navigation

import kotlinx.serialization.Serializable

sealed class ProfileRoutes {

    @Serializable
    data object InputProfile
}