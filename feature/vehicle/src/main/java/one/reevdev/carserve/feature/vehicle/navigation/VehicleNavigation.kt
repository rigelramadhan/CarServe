package one.reevdev.carserve.feature.vehicle.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.vehicle.screen.add.AddVehicleRouter
import one.reevdev.carserve.feature.vehicle.screen.list.VehicleListRouter

fun NavController.navigateToAddToCar() {
    navigate(VehicleRoutes.AddVehicle.route)
}

fun NavGraphBuilder.addVehicleScreen(
    onProceedForm: (vehicle: Vehicle) -> Unit
) {
    composable(route = VehicleRoutes.AddVehicle.route) {
        AddVehicleRouter(
            onProceedForm = onProceedForm
        )
    }
}

fun NavController.navigateToVehicleList() {
    navigate(VehicleRoutes.VehicleList.route)
}

fun NavGraphBuilder.vehicleListScreen(
    onAnalyzeVehicle: (vehicle: Vehicle) -> Unit
) {
    composable(route = VehicleRoutes.VehicleList.route) {
        VehicleListRouter(onAnalyzeVehicle = onAnalyzeVehicle)
    }
}