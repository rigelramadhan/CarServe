package one.reevdev.carserve.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.feature.common.ui.component.AppHeader

@Composable
fun HomeRouter(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onServeVisionClick: () -> Unit,
    onMyVehicleClick: () -> Unit,
    onAllAnalysisHistoryClick: () -> Unit,
    onAnalysisHistoryItemClick: (ServiceAnalysis) -> Unit,
    onServiceAdvisorClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAnalysisHistory()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeader(
                actions = {
                    IconButton(onClick = onServiceAdvisorClick) {
                        Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        HomeScreen(
            modifier = Modifier
                .padding(innerPadding),
            analysisHistory = uiState.analysisHistory,
            onServeVisionClick = onServeVisionClick,
            onAllAnalysisHistoryClick = onAllAnalysisHistoryClick,
            onMyVehicleClick = onMyVehicleClick,
            onAnalysisHistoryItemClick = onAnalysisHistoryItemClick
        )
    }
}