package one.reevdev.carserve.feature.vehicle.navigation

import kotlinx.serialization.Serializable
import one.reevdev.carserve.feature.common.ui.navigation.Route

sealed class VehicleRoutes {
    @Serializable
    data object Vehicle : Route

    @Serializable
    data object AddVehicle : Route

    @Serializable
    data object VehicleList : Route
}