package one.reevdev.carserve.feature.service.screen.detail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.core.domain.feature.profile.model.SavedProfile
import one.reevdev.carserve.core.domain.feature.service.model.ServiceFinding
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import one.reevdev.carserve.feature.service.screen.analysis.analysisDetail

@Composable
fun AnalysisDetailScreen(
    modifier: Modifier = Modifier,
    loadingState: LoadingState,
    vehicle: Vehicle?,
    profile: SavedProfile?,
    findings: List<ServiceFinding>?,
    recommendedAction: String,
    estimatedPrice: Double,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        analysisDetail(
            loadingState = loadingState,
            vehicle = vehicle,
            profile = profile,
            findings = findings,
            recommendedAction = recommendedAction,
            estimatedPrice = estimatedPrice
        )
    }
}