package one.reevdev.carserve.feature.service.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import one.reevdev.carserve.feature.service.screen.AnalysisRouter
import one.reevdev.carserve.feature.service.screen.analysis.ServiceAnalysisRouter
import one.reevdev.carserve.feature.service.screen.analysis.ServiceAnalysisViewModel
import one.reevdev.carserve.feature.service.screen.camera.AnalysisCameraRouter
import one.reevdev.carserve.feature.service.screen.symptom.SymptomFormRouter

fun NavController.navigateToCamera() {
    navigate(AnalysisRoutes.Camera.route)
}

fun NavGraphBuilder.cameraScreen(
    viewModel: ServiceAnalysisViewModel,
    proceedToForm: (Uri?) -> Unit,
) {
    composable(route = AnalysisRoutes.Camera.route) {
        AnalysisCameraRouter(
            viewModel = viewModel,
            proceedToForm = proceedToForm
        )
    }
}

fun NavController.navigateToForm() {
    navigate(AnalysisRoutes.Form.route)
}

fun NavGraphBuilder.formScreen(
    viewModel: ServiceAnalysisViewModel,
    proceedToAnalysis: () -> Unit,
) {
    composable(route = AnalysisRoutes.Form.route) {
        SymptomFormRouter(
            viewModel = viewModel,
            proceedToAnalysis = proceedToAnalysis
        )
    }
}

fun NavController.navigateToAnalysis() {
    navigate(AnalysisRoutes.Analysis.route)
}

fun NavGraphBuilder.analysisScreen(
    viewModel: ServiceAnalysisViewModel
) {
    composable(route = AnalysisRoutes.Analysis.route) {
        ServiceAnalysisRouter(viewModel = viewModel)
    }
}

fun NavGraphBuilder.serviceScreen() {
    composable(route = ServiceRoutes.Service.route) {
        AnalysisRouter()
    }
}
