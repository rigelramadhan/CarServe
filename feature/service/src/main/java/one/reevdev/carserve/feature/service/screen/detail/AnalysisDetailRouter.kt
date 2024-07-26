package one.reevdev.carserve.feature.service.screen.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.feature.common.ui.component.AppHeader
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import one.reevdev.carserve.feature.service.R

@Composable
fun AnalysisDetailRouter(
    modifier: Modifier = Modifier,
    serviceAnalysis: ServiceAnalysis,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeader(title = stringResource(R.string.label_analysis), hasBackButton = true)
        }
    ) { innerPadding ->
        with(serviceAnalysis) {
            AnalysisDetailScreen(
                modifier = Modifier
                    .padding(innerPadding),
                loadingState = LoadingState.NotLoading,
                vehicle = vehicle,
                profile = profile,
                findings = serviceFindings,
                recommendedAction = recommendedAction,
                estimatedPrice = totalEstimatedPrice
            )
        }
    }
}