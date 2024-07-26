package one.reevdev.carserve.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.R
import one.reevdev.carserve.core.domain.feature.profile.model.SavedProfile
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.core.domain.feature.service.model.ServiceFinding
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.common.ui.component.LabelText
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.feature.service.component.ServiceHistoryItem
import one.reevdev.carserve.ui.component.HomeCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    analysisHistory: List<ServiceAnalysis> = emptyList(),
    onServeVisionClick: () -> Unit,
    onMyVehicleClick: () -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp),
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                HomeCard(
                    title = stringResource(R.string.title_serve_vision),
                    description = stringResource(R.string.description_serve_vision),
                    icon = R.drawable.ic_verified_24,
                    onClick = onServeVisionClick
                )
                HomeCard(
                    title = stringResource(R.string.title_my_vehicles),
                    description = stringResource(R.string.description_my_vehicle),
                    onClick = onMyVehicleClick
                )
            }
        }
        if (analysisHistory.isNotEmpty()) {
            item {
                LabelText(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 4.dp),
                    label = stringResource(
                        R.string.message_last_analysis,
                        analysisHistory.size
                    )
                )
            }
            items(analysisHistory) { analysis ->
                ServiceHistoryItem(
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                    customer = analysis.profile?.email,
                    vehicle = analysis.vehicle,
                    findingCount = analysis.serviceFindings.size,
                    onItemClick = {

                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    CarServeTheme {
        val dummyAnalysis = ServiceAnalysis(
            vehicle = Vehicle(1, "Daihatsu", "Black", "Automatic"),
            profile = SavedProfile(
                "John Doe",
                "johndoe@email.com",
                "081311048587",
                "Jl. Address"
            ),
            recommendedAction = "This is the recommended action",
            serviceFindings = listOf(
                ServiceFinding(
                    "Problem 1",
                    "Solution 1",
                    25000.0
                ),
                ServiceFinding(
                    "Problem 1",
                    "Solution 1",
                    25000.0
                ),
                ServiceFinding(
                    "Problem 1",
                    "Solution 1",
                    25000.0
                ),
            ),
            totalEstimatedPrice = 75000.0,
            analysisHtml = ""
        )
        HomeScreen(
            onServeVisionClick = {},
            analysisHistory = listOf(
                dummyAnalysis,
                dummyAnalysis,
                dummyAnalysis,
            )
        ) {

        }
    }
}