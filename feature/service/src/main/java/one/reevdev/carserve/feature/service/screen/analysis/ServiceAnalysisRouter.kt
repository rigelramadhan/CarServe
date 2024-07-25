package one.reevdev.carserve.feature.service.screen.analysis

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import one.reevdev.carserve.feature.common.ui.component.AppHeader
import one.reevdev.carserve.feature.service.R
import one.reevdev.carserve.feature.service.screen.ServiceAnalysisViewModel
import one.reevdev.carserve.feature.service.utils.DocumentHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceAnalysisRouter(
    modifier: Modifier = Modifier,
    viewModel: ServiceAnalysisViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    navigateBack: () -> Unit,
    onProceed: () -> Unit,
    navigateToPdfViewer: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.analyzeVehicle(uiState.param)
    }

    Scaffold(
        topBar = {
            AppHeader(
                title = stringResource(id = R.string.label_analysis),
                navigateBack = navigateBack
            )
        }
    ) { innerPadding ->
        ServiceAnalysisScreen(
            modifier = modifier
                .padding(innerPadding),
            vehicle = uiState.serviceAnalysis.vehicle,
            profile = uiState.serviceAnalysis.profile,
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
}