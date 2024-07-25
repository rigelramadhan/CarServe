package one.reevdev.carserve.feature.vehicle

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.vehicle.navigation.VehicleRoutes
import one.reevdev.carserve.feature.vehicle.navigation.addVehicleScreen
import one.reevdev.carserve.feature.vehicle.navigation.navigateToAddToCar
import one.reevdev.carserve.feature.vehicle.navigation.vehicleListScreen

@Composable
fun VehicleRouter(
    modifier: Modifier = Modifier,
    startDestination: Any = VehicleRoutes.VehicleList,
    navController: NavHostController = rememberNavController(),
    onAnalyzeVehicle: (Vehicle) -> Unit,
) {
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = startDestination
        ) {
            vehicleListScreen(
                onAddVehicle = { navController.navigateToAddToCar() },
                onAnalyzeVehicle = onAnalyzeVehicle
            )
            addVehicleScreen(
                shouldShowCarOptions = false,
                navigateBack = { navController.navigateUp() },
                onSubmitVehicle = { navController.navigateUp() }
            )
        }
    }
}