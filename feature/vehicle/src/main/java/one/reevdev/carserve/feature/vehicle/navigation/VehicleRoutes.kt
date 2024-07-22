package one.reevdev.carserve.feature.vehicle.navigation

import kotlinx.serialization.Serializable

sealed class VehicleRoutes {
    @Serializable
    data object Vehicle

    @Serializable
    data object AddVehicle

    @Serializable
    data object VehicleList
}