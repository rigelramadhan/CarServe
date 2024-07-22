package one.reevdev.carserve.feature.vehicle.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.vehicle.VehicleRouter
import one.reevdev.carserve.feature.vehicle.screen.add.AddVehicleRouter
import one.reevdev.carserve.feature.vehicle.screen.list.VehicleListRouter

fun NavController.navigateToAddToCar() {
    navigate(VehicleRoutes.AddVehicle.route)
}

fun NavGraphBuilder.addVehicleScreen(
    shouldShowCarOptions: Boolean = true,
    onSubmitVehicle: (vehicle: Vehicle) -> Unit
) {
    composable(route = VehicleRoutes.AddVehicle.route) {
        AddVehicleRouter(
            shouldShowCarOptions = shouldShowCarOptions,
            onSubmitVehicle = onSubmitVehicle
        )
    }
}

fun NavController.navigateToVehicleList() {
    navigate(VehicleRoutes.VehicleList.route)
}

fun NavGraphBuilder.vehicleListScreen(
    onAddVehicle: () -> Unit,
    onAnalyzeVehicle: (vehicle: Vehicle) -> Unit
) {
    composable(route = VehicleRoutes.VehicleList.route) {
        VehicleListRouter(onAddVehicle = onAddVehicle, onAnalyzeVehicle = onAnalyzeVehicle)
    }
}

fun NavController.navigateToVehicle() {
    navigate(VehicleRoutes.Vehicle.route)
}

fun NavGraphBuilder.vehicleScreen(
    onAnalyzeVehicle: (vehicle: Vehicle) -> Unit
) {
    composable(route = VehicleRoutes.Vehicle.route) {
        VehicleRouter(
            onAnalyzeVehicle = onAnalyzeVehicle
        )
    }
}