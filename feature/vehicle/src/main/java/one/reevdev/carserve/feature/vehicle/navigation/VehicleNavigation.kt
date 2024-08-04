package one.reevdev.carserve.feature.vehicle.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle
import one.reevdev.carserve.feature.vehicle.VehicleRouter
import one.reevdev.carserve.feature.vehicle.screen.add.AddVehicleRouter
import one.reevdev.carserve.feature.vehicle.screen.list.VehicleListRouter

fun NavController.navigateToAddToCar() {
    navigate(VehicleRoutes.AddVehicle)
}

fun NavGraphBuilder.addVehicleScreen(
    shouldShowCarOptions: Boolean = false,
    onSubmitVehicle: (vehicle: CustomerVehicle) -> Unit
) {
    composable<VehicleRoutes.AddVehicle> {
        AddVehicleRouter(
            shouldShowCarOptions = shouldShowCarOptions,
            onSubmitVehicle = onSubmitVehicle,
        )
    }
}

fun NavController.navigateToVehicleList() {
    navigate(VehicleRoutes.VehicleList)
}

fun NavGraphBuilder.vehicleListScreen(
    onAddVehicle: () -> Unit,
    onAnalyzeVehicle: (vehicle: CustomerVehicle) -> Unit
) {
    composable<VehicleRoutes.VehicleList> {
        VehicleListRouter(
            onAddVehicle = onAddVehicle,
            onAnalyzeVehicle = onAnalyzeVehicle
        )
    }
}

fun NavController.navigateToVehicle() {
    navigate(VehicleRoutes.Vehicle)
}

fun NavGraphBuilder.vehicleRouter(
    onAnalyzeVehicle: (vehicle: CustomerVehicle) -> Unit
) {
    composable<VehicleRoutes.Vehicle> {
        VehicleRouter(
            onAnalyzeVehicle = onAnalyzeVehicle
        )
    }
}