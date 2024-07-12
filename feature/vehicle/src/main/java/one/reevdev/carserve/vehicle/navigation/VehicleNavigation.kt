package one.reevdev.carserve.vehicle.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import one.reevdev.carserve.core.domain.feature.vehicle.model.VehicleParam
import one.reevdev.carserve.vehicle.screen.AddVehicleRouter

fun NavController.navigateToAddToCar() {
    navigate(VehicleRoutes.AddVehicle.route)
}

fun NavGraphBuilder.addVehicleScreen(
    onProceedForm: (param: VehicleParam) -> Unit
) {
    composable(route = VehicleRoutes.AddVehicle.route) {
        AddVehicleRouter(
            onProceedForm = onProceedForm
        )
    }
}