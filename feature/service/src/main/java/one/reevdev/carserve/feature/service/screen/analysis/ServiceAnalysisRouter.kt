package one.reevdev.carserve.feature.service.screen.analysis

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import one.reevdev.carserve.core.domain.model.service.ServiceParam
import one.reevdev.carserve.feature.common.ui.component.LoadingDialog

@Composable
fun ServiceAnalysisRouter(
    modifier: Modifier = Modifier,
    viewModel: ServiceAnalysisViewModel = hiltViewModel(),
    param: ServiceParam,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.analyzeVehicle(param)
    }

    Scaffold { innerPadding ->
        ServiceAnalysisScreen(
            modifier = modifier
                .padding(innerPadding),
            findings = uiState.serviceAnalysis.serviceFindings,
            recommendedAction = uiState.serviceAnalysis.recommendedAction,
            estimatedPrice = uiState.serviceAnalysis.totalEstimatedPrice
        )
    }

    if (uiState.isLoading) {
        LoadingDialog()
    }
}