package one.reevdev.carserve.feature.service.screen.history

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.feature.common.ui.component.AppHeader
import one.reevdev.carserve.feature.service.R

@Composable
fun AnalysisHistoryRouter(
    modifier: Modifier = Modifier,
    viewModel: AnalysisHistoryViewModel = hiltViewModel(),
    onItemClick: (ServiceAnalysis) -> Unit,
    onPhoneClick: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAnalysisHistory()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeader(title = stringResource(R.string.title_analysis_history), hasBackButton = true)
        }
    ) { innerPadding ->
        AnalysisHistoryScreen(
            modifier = Modifier
                .padding(innerPadding),
            analysisHistory = uiState.analysisHistory,
            onItemClick = onItemClick,
            onPhoneClick = onPhoneClick
        )
    }
}