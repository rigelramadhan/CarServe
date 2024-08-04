package one.reevdev.carserve.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle
import one.reevdev.carserve.ui.screen.MainRouter
import one.reevdev.carserve.ui.screen.home.HomeRouter
import one.reevdev.carserve.ui.screen.splash.SplashRouter

fun NavController.navigateToHome(clearBackStack: Boolean = false) {
    navigate(MainRoutes.Home) {
        if (clearBackStack) {
            popUpTo(MainRoutes.Home) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.homeScreen(
    onServeVisionClick: () -> Unit,
    onMyVehicleClick: () -> Unit,
    onAllAnalysisHistoryClick: () -> Unit,
    onAnalysisHistoryItemClick: (ServiceAnalysis) -> Unit,
    onServiceAdvisorClick: () -> Unit
) {
    composable<MainRoutes.Home> {
        HomeRouter(
            onServeVisionClick = onServeVisionClick,
            onMyVehicleClick = onMyVehicleClick,
            onAllAnalysisHistoryClick = onAllAnalysisHistoryClick,
            onAnalysisHistoryItemClick = onAnalysisHistoryItemClick,
            onServiceAdvisorClick = onServiceAdvisorClick
        )
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

fun NavController.navigateToMain(clearBackStack: Boolean = false) {
    navigate(MainRoutes.Main) {
        if (clearBackStack) {
            popUpTo(MainRoutes.Main) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.mainRouter(
    navigateToService: (CustomerVehicle) -> Unit,
    navigateToServiceHistory: () -> Unit,
    navigateToServiceDetail: (ServiceAnalysis) -> Unit,
    navigateToServiceAdvisor: () -> Unit,
    onLoggedOut: () -> Unit
) {
    composable<MainRoutes.Main> {
        MainRouter(
            navigateToService = navigateToService,
            navigateToAnalysisHistory = navigateToServiceHistory,
            navigateToAnalysisDetail = navigateToServiceDetail,
            navigateToServiceAdvisor = navigateToServiceAdvisor,
            onLoggedOut = onLoggedOut,
        )
    }
}