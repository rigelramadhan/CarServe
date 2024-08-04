package one.reevdev.carserve.feature.profile.navigation

import kotlinx.serialization.Serializable
import one.reevdev.carserve.feature.common.ui.navigation.Route

sealed class ProfileRoutes {

    @Serializable
    data object InputCustomer : Route

    @Serializable
    data object ServiceAdvisor : Route

    @Serializable
    data object CustomerList : Route
}