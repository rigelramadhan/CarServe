package one.reevdev.carserve.feature.service.screen.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.core.common.data.toRupiahCurrency
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.feature.service.component.ServiceHistoryItem

@Composable
fun AnalysisHistoryScreen(
    modifier: Modifier = Modifier,
    analysisHistory: List<ServiceAnalysis>,
    onItemClick: (ServiceAnalysis) -> Unit,
    onPhoneClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(analysisHistory) { analysis ->
            with(analysis) {
                ServiceHistoryItem(
                    customerName = profile.email,
                    customerPhoneNo = profile.phoneNumber,
                    vehicle = vehicle,
                    findingCount = analysisHistory.size,
                    estimatedPrice = analysis.totalEstimatedPrice.toRupiahCurrency(),
                    onItemClick = {
                        onItemClick(analysis)
                    },
                    onPhoneClick = {
                        onPhoneClick(analysis.profile.phoneNumber)
                    }
                )
            }
        }
    }
}