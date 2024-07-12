package one.reevdev.carserve.feature.service.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import one.reevdev.carserve.feature.common.ui.component.LoadingDialog
import one.reevdev.carserve.feature.service.navigation.analysisScreen
import one.reevdev.carserve.feature.service.navigation.cameraScreen
import one.reevdev.carserve.feature.service.navigation.formScreen
import one.reevdev.carserve.feature.service.navigation.navigateToAnalysis
import one.reevdev.carserve.feature.service.navigation.navigateToForm
import one.reevdev.carserve.feature.service.navigation.routes.AnalysisRoutes
import one.reevdev.carserve.vehicle.navigation.addVehicleScreen
import one.reevdev.carserve.vehicle.navigation.navigateToAddToCar

@Composable
fun AnalysisRouter(
    modifier: Modifier = Modifier,
    viewModel: ServiceAnalysisViewModel = hiltViewModel(),
    startDestination: String = AnalysisRoutes.Camera.route,
    navController: NavHostController = rememberNavController(),
    navigateToHome: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = uiState.errorMessage) {
        uiState.errorMessage?.let {
            scope.launch {
                snackbarHostState.showSnackbar(it)
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        NavHost(
            modifier = Modifier
                .padding(it),
            navController = navController,
            startDestination = startDestination
        ) {
            cameraScreen(viewModel) {
                navController.navigateToAddToCar()
                viewModel.setLoading(false)
            }
            addVehicleScreen { vehicle ->
                viewModel.setVehicle(vehicle)
                navController.navigateToForm()
            }
            formScreen(viewModel) {
                navController.navigateToAnalysis()
            }
            analysisScreen(viewModel) {
                navigateToHome()
            }
        }
    }

    if (uiState.isLoading) {
        LoadingDialog()
    }
}