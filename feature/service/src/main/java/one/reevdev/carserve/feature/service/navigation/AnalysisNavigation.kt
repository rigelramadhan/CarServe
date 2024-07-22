package one.reevdev.carserve.feature.service.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.service.navigation.routes.AnalysisRoutes
import one.reevdev.carserve.feature.service.navigation.routes.ServiceRoutes
import one.reevdev.carserve.feature.service.screen.AnalysisRouter
import one.reevdev.carserve.feature.service.screen.ServiceAnalysisViewModel
import one.reevdev.carserve.feature.service.screen.analysis.ServiceAnalysisRouter
import one.reevdev.carserve.feature.service.screen.camera.AnalysisCameraRouter
import one.reevdev.carserve.feature.service.screen.pdfviewer.PdfViewerScreen
import one.reevdev.carserve.feature.service.screen.symptom.SymptomFormRouter
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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
    viewModel: ServiceAnalysisViewModel,
    onProceed: () -> Unit,
    navigateToPdfViewer: (String) -> Unit,
) {
    composable(route = AnalysisRoutes.Analysis.route) {
        ServiceAnalysisRouter(
            viewModel = viewModel,
            onProceed = onProceed,
            navigateToPdfViewer = navigateToPdfViewer
        )
    }
}

fun NavController.navigateToService(initialVehicleJson: Vehicle = Vehicle()) {
    val initVehicleJson = initialVehicleJson.run { Gson().toJson(this) }
    val encodedVehicle = URLEncoder.encode(initVehicleJson, StandardCharsets.UTF_8.toString())
    navigate(ServiceRoutes.Service.getRoute(encodedVehicle))
}

fun NavGraphBuilder.serviceScreen(navigateToHome: () -> Unit) {
    composable(
        route = ServiceRoutes.Service.route,
        arguments = listOf(navArgument(RouteConstants.ARGUMENT_INIT_VEHICLE) {
            type = NavType.StringType
            nullable = true
        })
    ) {
        val initVehicleJson = it.arguments?.getString(RouteConstants.ARGUMENT_INIT_VEHICLE)
        val decodedInitVehicle = initVehicleJson?.run {
            val decodedJson = URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
            Gson().fromJson(decodedJson, Vehicle::class.java)
        }
        AnalysisRouter(navigateToHome = navigateToHome, initVehicle = decodedInitVehicle)
    }
}

fun NavController.navigateToPdfViewer(path: String) {
    val encodedPath = URLEncoder.encode(path, StandardCharsets.UTF_8.toString())
    navigate(AnalysisRoutes.PdfViewer.getRoute(encodedPath))
}

fun NavGraphBuilder.pdfViewerScreen() {
    composable(
        route = AnalysisRoutes.PdfViewer.route,
        arguments = listOf(navArgument(RouteConstants.ARGUMENT_PDF_PATH) {
            type = NavType.StringType
        })
    ) {
        val pdfFilePath = it.arguments?.getString(RouteConstants.ARGUMENT_PDF_PATH).orEmpty()
        val decodedPath = URLDecoder.decode(pdfFilePath, StandardCharsets.UTF_8.toString())
        PdfViewerScreen(pdfFilePath = decodedPath)
    }
}
