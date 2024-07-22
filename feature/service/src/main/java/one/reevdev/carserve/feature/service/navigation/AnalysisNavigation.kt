package one.reevdev.carserve.feature.service.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.service.navigation.routes.AnalysisRoutes
import one.reevdev.carserve.feature.service.navigation.routes.ServiceRoutes
import one.reevdev.carserve.feature.service.navigation.routes.VehicleParameterType
import one.reevdev.carserve.feature.service.screen.AnalysisRouter
import one.reevdev.carserve.feature.service.screen.ServiceAnalysisViewModel
import one.reevdev.carserve.feature.service.screen.analysis.ServiceAnalysisRouter
import one.reevdev.carserve.feature.service.screen.camera.AnalysisCameraRouter
import one.reevdev.carserve.feature.service.screen.pdfviewer.PdfViewerScreen
import one.reevdev.carserve.feature.service.screen.symptom.SymptomFormRouter
import kotlin.reflect.typeOf

fun NavController.navigateToCamera() {
    navigate(AnalysisRoutes.Camera)
}

fun NavGraphBuilder.cameraScreen(
    viewModel: ServiceAnalysisViewModel,
    proceedToForm: (Uri?) -> Unit,
) {
    composable<AnalysisRoutes.Camera> {
        AnalysisCameraRouter(
            viewModel = viewModel,
            proceedToForm = proceedToForm
        )
    }
}

fun NavController.navigateToForm() {
    navigate(AnalysisRoutes.Form)
}

fun NavGraphBuilder.formScreen(
    viewModel: ServiceAnalysisViewModel,
    proceedToAnalysis: () -> Unit,
) {
    composable<AnalysisRoutes.Form> {
        SymptomFormRouter(
            viewModel = viewModel,
            proceedToAnalysis = proceedToAnalysis
        )
    }
}

fun NavController.navigateToAnalysis() {
    navigate(AnalysisRoutes.Analysis)
}

fun NavGraphBuilder.analysisScreen(
    viewModel: ServiceAnalysisViewModel,
    onProceed: () -> Unit,
    navigateToPdfViewer: (String) -> Unit,
) {
    composable<AnalysisRoutes.Analysis> {
        ServiceAnalysisRouter(
            viewModel = viewModel,
            onProceed = onProceed,
            navigateToPdfViewer = navigateToPdfViewer
        )
    }
}

fun NavController.navigateToService(initialVehicle: Vehicle = Vehicle()) {
    navigate(ServiceRoutes.Service(initialVehicle))
}

fun NavGraphBuilder.serviceScreen(navigateToHome: () -> Unit) {
    composable<ServiceRoutes.Service>(
        typeMap = mapOf(typeOf<Vehicle>() to VehicleParameterType)
    ) {
        val initVehicle = it.toRoute<ServiceRoutes.Service>().initVehicle
        AnalysisRouter(navigateToHome = navigateToHome, initVehicle = initVehicle)
    }
}

fun NavController.navigateToPdfViewer(path: String) {
    navigate(AnalysisRoutes.PdfViewer(path))
}

fun NavGraphBuilder.pdfViewerScreen() {
    composable<AnalysisRoutes.PdfViewer> {
        val pdfFilePath = it.toRoute<AnalysisRoutes.PdfViewer>().pdfPath
        PdfViewerScreen(pdfFilePath = pdfFilePath)
    }
}
