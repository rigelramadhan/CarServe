package one.reevdev.carserve.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import one.reevdev.carserve.feature.service.navigation.ServiceRoutes
import one.reevdev.carserve.feature.service.navigation.serviceScreen

@Composable
fun CarServeApp(
    modifier: Modifier = Modifier,
    startDestination: String = ServiceRoutes.Service.route,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        serviceScreen()
    }
}