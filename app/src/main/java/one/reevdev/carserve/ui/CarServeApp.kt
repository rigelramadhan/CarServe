package one.reevdev.carserve.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import one.reevdev.carserve.feature.auth.navigation.authRouter
import one.reevdev.carserve.feature.auth.navigation.navigateToLogin
import one.reevdev.carserve.feature.profile.navigation.navigateToServiceAdvisor
import one.reevdev.carserve.feature.profile.navigation.serviceAdvisorScreen
import one.reevdev.carserve.feature.service.navigation.analysisDetailScreen
import one.reevdev.carserve.feature.service.navigation.analysisHistoryScreen
import one.reevdev.carserve.feature.service.navigation.navigateToAnalysisDetail
import one.reevdev.carserve.feature.service.navigation.navigateToAnalysisHistory
import one.reevdev.carserve.feature.service.navigation.navigateToService
import one.reevdev.carserve.feature.service.navigation.serviceRouter
import one.reevdev.carserve.ui.navigation.MainRoutes
import one.reevdev.carserve.ui.navigation.mainRouter
import one.reevdev.carserve.ui.navigation.navigateToMain
import one.reevdev.carserve.ui.navigation.splashScreen

@Composable
fun CarServeApp(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
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
                navigateToServiceHistory = {
                    navController.navigateToAnalysisHistory()
                },
                navigateToServiceDetail = {
                    navController.navigateToAnalysisDetail(it)
                },
                navigateToServiceAdvisor = {
                    navController.navigateToServiceAdvisor()
                },
                onLoggedOut = {
                    navController.navigateToLogin()
                },
                onPhoneClick = {
                    context.startActivity(
                        Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:$it")
                        }
                    )
                }
            )
            serviceRouter(
                navigateToHome = { navController.navigateToMain(clearBackStack = true) }
            )
            analysisHistoryScreen(
                onItemClick = { navController.navigateToAnalysisDetail(it) },
                onPhoneClick = {
                    context.startActivity(
                        Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:$it")
                        }
                    )
                }
            )
            analysisDetailScreen()
            serviceAdvisorScreen()
        }
    }
}