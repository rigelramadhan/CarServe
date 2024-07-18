package one.reevdev.carserve.vehicle.navigation

sealed class VehicleRoutes(val route: String) {
    data object AddVehicle : VehicleRoutes(VehicleConstants.ADD_VEHICLE)
    data object VehicleList : VehicleRoutes(VehicleConstants.VEHICLE_LIST)
}