package one.reevdev.carserve.feature.vehicle.navigation

sealed class VehicleRoutes(val route: String) {
    data object Vehicle : VehicleRoutes(VehicleConstants.ADD_VEHICLE)
    data object AddVehicle : VehicleRoutes(VehicleConstants.ADD_VEHICLE)
    data object VehicleList : VehicleRoutes(VehicleConstants.VEHICLE_LIST)
}