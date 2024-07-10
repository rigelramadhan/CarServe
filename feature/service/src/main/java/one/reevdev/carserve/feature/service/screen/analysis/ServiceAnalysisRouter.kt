package one.reevdev.carserve.feature.service.screen.analysis

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import one.reevdev.carserve.feature.common.ui.component.LoadingDialog

@Composable
fun ServiceAnalysisRouter(
    modifier: Modifier = Modifier,
    viewModel: ServiceAnalysisViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.analyzeVehicle(uiState.param)
    }

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        ServiceAnalysisScreen(
            modifier = Modifier
                .padding(innerPadding),
            findings = uiState.serviceAnalysis.serviceFindings,
            recommendedAction = uiState.serviceAnalysis.recommendedAction,
            estimatedPrice = uiState.serviceAnalysis.totalEstimatedPrice,
            image = uiState.param.photo
        )
    }

    if (uiState.isLoading) {
        LoadingDialog()
    }
}