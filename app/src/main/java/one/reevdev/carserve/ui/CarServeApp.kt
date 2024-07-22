package one.reevdev.carserve.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import one.reevdev.carserve.feature.service.navigation.navigateToService
import one.reevdev.carserve.feature.service.navigation.serviceScreen
import one.reevdev.carserve.feature.vehicle.navigation.navigateToVehicleList
import one.reevdev.carserve.feature.vehicle.navigation.vehicleListScreen
import one.reevdev.carserve.ui.navigation.MainRoutes
import one.reevdev.carserve.ui.navigation.homeScreen
import one.reevdev.carserve.ui.navigation.navigateToHome

@Composable
fun CarServeApp(
    modifier: Modifier = Modifier,
    startDestination: String = MainRoutes.Home.route,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        homeScreen(
            onServeVisionClick = { navController.navigateToService() },
            onMyVehicleClick = { navController.navigateToVehicleList() }
        )
        serviceScreen { navController.navigateToHome(clearBackStack = true) }
        vehicleListScreen { vehicle ->
            navController.navigateToService(vehicle)
        }
    }
}