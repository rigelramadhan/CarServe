package one.reevdev.carserve.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.R
import one.reevdev.carserve.core.domain.feature.profile.model.Customer
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.core.domain.feature.service.model.ServiceFinding
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerWithVehicle
import one.reevdev.carserve.feature.common.ui.component.LabelText
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.feature.service.component.ServiceHistoryItem
import one.reevdev.carserve.ui.component.HomeCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    analysisHistory: List<ServiceAnalysis> = emptyList(),
    recentCustomer: List<CustomerWithVehicle> = emptyList(),
    onServeVisionClick: () -> Unit,
    onMyVehicleClick: () -> Unit,
    onAllAnalysisHistoryClick: () -> Unit,
    onAnalysisHistoryItemClick: (ServiceAnalysis) -> Unit,
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
                    drawable = R.drawable.car_analysis_illustration,
                    onClick = onServeVisionClick
                )
            }
        }
        if (analysisHistory.isNotEmpty()) {
            item {
                Row(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LabelText(
                        modifier = Modifier
                            .weight(1f),
                        label = stringResource(
                            R.string.message_last_analysis,
                            analysisHistory.size
                        )
                    )
                    TextButton(onClick = { onAllAnalysisHistoryClick() }) {
                        Text(text = stringResource(R.string.label_see_all))
                    }
                }
            }
            items(analysisHistory) { analysis ->
                ServiceHistoryItem(
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                    customer = analysis.profile?.email,
                    vehicle = analysis.vehicle,
                    findingCount = analysis.serviceFindings.size,
                    onItemClick = {
                        onAnalysisHistoryItemClick(analysis)
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
            vehicle = CustomerVehicle(
                policeNo = "AG 2446 NB",
                ownerEmail = "john@doe.com",
                carBrand = "Brand 1",
                carName = "Car Name 1",
                color = "Color 1",
                carType = "Car Type 1",
                transmission = "Transmission"
            ),
            profile = Customer(
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
            ),
            onAllAnalysisHistoryClick = {},
            onAnalysisHistoryItemClick = {},
            onMyVehicleClick = {}
        )
    }
}