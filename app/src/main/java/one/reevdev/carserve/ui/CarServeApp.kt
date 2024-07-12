package one.reevdev.carserve.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import one.reevdev.carserve.feature.service.navigation.navigateToService
import one.reevdev.carserve.feature.service.navigation.serviceScreen
import one.reevdev.carserve.ui.navigation.MainRoutes
import one.reevdev.carserve.ui.navigation.homeScreen

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
        homeScreen { navController.navigateToService() }
        serviceScreen()
    }
}