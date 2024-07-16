package one.reevdev.carserve.feature.service.screen.analysis

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import one.reevdev.carserve.feature.service.screen.ServiceAnalysisViewModel
import one.reevdev.carserve.feature.service.utils.DocumentHelper

@Composable
fun ServiceAnalysisRouter(
    modifier: Modifier = Modifier,
    viewModel: ServiceAnalysisViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    onProceed: () -> Unit,
    navigateToPdfViewer: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.analyzeVehicle(uiState.param)
    }

    ServiceAnalysisScreen(
        modifier = modifier,
        vehicle = uiState.serviceAnalysis.vehicle,
        loadingState = uiState.loadingState,
        findings = uiState.serviceAnalysis.serviceFindings,
        recommendedAction = uiState.serviceAnalysis.recommendedAction,
        estimatedPrice = uiState.serviceAnalysis.totalEstimatedPrice,
        image = uiState.param.photo,
        onProceed = onProceed,
        onExportPdf = {
            if (uiState.serviceAnalysis.analysisHtml.isNotBlank()) {
                DocumentHelper.printPdf(
                    analysisResultHtml = uiState.serviceAnalysis.analysisHtml,
                    context = context,
                    onViewerUnavailable = navigateToPdfViewer
                )
            }
        }
    )
}