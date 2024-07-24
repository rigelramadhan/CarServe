package one.reevdev.carserve.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import one.reevdev.carserve.feature.auth.navigation.authRouter
import one.reevdev.carserve.feature.auth.navigation.navigateToLogin
import one.reevdev.carserve.feature.service.navigation.navigateToService
import one.reevdev.carserve.feature.service.navigation.serviceRouter
import one.reevdev.carserve.ui.navigation.MainRoutes
import one.reevdev.carserve.ui.navigation.mainRouter
import one.reevdev.carserve.ui.navigation.navigateToMain
import one.reevdev.carserve.ui.navigation.splashScreen

@Composable
fun CarServeApp(
    modifier: Modifier = Modifier,
    startDestination: Any = MainRoutes.Splash,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .padding(innerPadding),
            navController = navController,
            startDestination = startDestination
        ) {
            splashScreen(
                navigateToHome = { navController.navigateToMain(clearBackStack = true) },
                navigateToAuth = { navController.navigateToLogin() }
            )
            authRouter(
                navigateToHome = { navController.navigateToMain(clearBackStack = true) }
            )
            mainRouter(
                navigateToService = { vehicle ->
                    navController.navigateToService(vehicle)
                },
                onLoggedOut = {
                    navController.navigateToLogin()
                }
            )
            serviceRouter(
                navigateToHome = { navController.navigateToMain(clearBackStack = true) }
            )
        }
    }
}