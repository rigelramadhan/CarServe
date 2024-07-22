package one.reevdev.carserve.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import one.reevdev.carserve.ui.screen.home.HomeRouter

fun NavController.navigateToHome(clearBackStack: Boolean = false) {
    val navOptions = if (clearBackStack) {
        NavOptions.Builder()
            .setPopUpTo(graph.startDestinationId, inclusive = true)
            .setLaunchSingleTop(true)
            .build()
    } else null
    navigate(route = MainRoutes.Home.route, navOptions = navOptions)
}

fun NavGraphBuilder.homeScreen(onServeVisionClick: () -> Unit, onMyVehicleClick: () -> Unit) {
    composable(route = MainRoutes.Home.route) {
        HomeRouter(onServeVisionClick = onServeVisionClick, onMyVehicleClick = onMyVehicleClick)
    }
}