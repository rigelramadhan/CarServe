package one.reevdev.carserve.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import one.reevdev.carserve.feature.profile.navigation.authRouter
import one.reevdev.carserve.feature.profile.navigation.navigateToLogin
import one.reevdev.carserve.feature.service.navigation.navigateToService
import one.reevdev.carserve.feature.service.navigation.serviceRouter
import one.reevdev.carserve.feature.vehicle.navigation.navigateToVehicle
import one.reevdev.carserve.feature.vehicle.navigation.vehicleRouter
import one.reevdev.carserve.ui.navigation.MainRoutes
import one.reevdev.carserve.ui.navigation.homeScreen
import one.reevdev.carserve.ui.navigation.navigateToHome
import one.reevdev.carserve.ui.navigation.splashScreen

@Composable
fun CarServeApp(
    modifier: Modifier = Modifier,
    startDestination: Any = MainRoutes.Splash,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        splashScreen(
            navigateToHome = { navController.navigateToHome(clearBackStack = true) },
            navigateToAuth = { navController.navigateToLogin() }
        )
        authRouter(
            navigateToHome = { navController.navigateToHome(clearBackStack = true) }
        )
        homeScreen(
            onServeVisionClick = { navController.navigateToService() },
            onMyVehicleClick = { navController.navigateToVehicle() }
        )
        serviceRouter(
            navigateToHome = { navController.navigateToHome(clearBackStack = true) }
        )
        vehicleRouter(
            onAnalyzeVehicle = { vehicle ->
                navController.navigateToService(vehicle)
            }
        )
    }
}