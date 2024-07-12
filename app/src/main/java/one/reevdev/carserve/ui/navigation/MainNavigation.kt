package one.reevdev.carserve.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import one.reevdev.carserve.ui.screen.home.HomeRouter

fun NavGraphBuilder.homeScreen(onServeVisionClick: () -> Unit) {
    composable(route = MainRoutes.Home.route) {
        HomeRouter {
            onServeVisionClick()
        }
    }
}