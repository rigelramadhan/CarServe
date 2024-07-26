package one.reevdev.carserve.feature.service.screen.history

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.feature.service.component.ServiceHistoryItem

@Composable
fun AnalysisHistoryScreen(
    modifier: Modifier = Modifier,
    analysisHistory: List<ServiceAnalysis>,
    onItemClick: (ServiceAnalysis) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(analysisHistory) { analysis ->
            with(analysis) {
                ServiceHistoryItem(
                    customer = profile?.email,
                    vehicle = vehicle,
                    findingCount = analysisHistory.size,
                    onItemClick = {
                        onItemClick(analysis)
                    }
                )
            }
        }
    }
}