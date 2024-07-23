package one.reevdev.carserve.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import one.reevdev.carserve.ui.screen.home.HomeRouter
import one.reevdev.carserve.ui.screen.splash.SplashRouter

fun NavController.navigateToHome(clearBackStack: Boolean = false) {
    val navOptions = if (clearBackStack) {
        NavOptions.Builder()
            .setPopUpTo(graph.startDestinationId, inclusive = true)
            .setLaunchSingleTop(true)
            .build()
    } else null
    navigate(MainRoutes.Home, navOptions = navOptions)
}

fun NavGraphBuilder.homeScreen(onServeVisionClick: () -> Unit, onMyVehicleClick: () -> Unit) {
    composable<MainRoutes.Home> {
        HomeRouter(onServeVisionClick = onServeVisionClick, onMyVehicleClick = onMyVehicleClick)
    }
}

fun NavGraphBuilder.splashScreen(navigateToHome: () -> Unit, navigateToAuth: () -> Unit) {
    composable<MainRoutes.Splash> {
        SplashRouter(
            navigateToHome = navigateToHome,
            navigateToLogin = navigateToAuth
        )
    }
}