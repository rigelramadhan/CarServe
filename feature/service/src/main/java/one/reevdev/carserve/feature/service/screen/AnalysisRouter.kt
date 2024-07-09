package one.reevdev.carserve.feature.service.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import one.reevdev.carserve.feature.service.navigation.AnalysisRoutes
import one.reevdev.carserve.feature.service.navigation.analysisScreen
import one.reevdev.carserve.feature.service.navigation.cameraScreen
import one.reevdev.carserve.feature.service.navigation.formScreen
import one.reevdev.carserve.feature.service.navigation.navigateToAnalysis
import one.reevdev.carserve.feature.service.navigation.navigateToForm

@Composable
fun AnalysisRouter(
    modifier: Modifier = Modifier,
    viewModel: AnalysisSharedViewModel = hiltViewModel(),
    startDestination: String = AnalysisRoutes.Camera.route,
    navController: NavHostController = rememberNavController(),
) {
    val param by viewModel.param.collectAsStateWithLifecycle()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        cameraScreen(viewModel) {
            navController.navigateToForm()
        }
        formScreen(viewModel) {
            navController.navigateToAnalysis()
        }
        analysisScreen(param)
    }
}