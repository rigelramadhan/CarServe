package one.reevdev.carserve.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.R
import one.reevdev.carserve.core.domain.feature.profile.model.Customer
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerWithVehicle
import one.reevdev.carserve.feature.common.ui.component.EmptyComponent
import one.reevdev.carserve.feature.common.ui.component.LabelText
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.ui.component.HomeCard
import one.reevdev.carserve.ui.component.HomeRecentCustomer
import one.reevdev.carserve.feature.common.R as CommonRes

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    greeting: String,
    recentCustomer: List<CustomerWithVehicle> = emptyList(),
    onServeVisionClick: () -> Unit,
    onAllAnalysisHistoryClick: () -> Unit,
    onServiceAdvisorClick: () -> Unit,
    onRecentCustomerClick: (CustomerWithVehicle) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable { onServiceAdvisorClick() }
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                painter = painterResource(id = CommonRes.drawable.placeholder_mechanic_avatar),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .padding(start = 18.dp)
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier,
                    text = greeting,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.label_mechanic),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
        HomeCard(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp, top = 36.dp),
            title = stringResource(R.string.title_serve_vision),
            description = stringResource(R.string.description_serve_vision),
            drawable = R.drawable.car_analysis_illustration,
            onClick = onServeVisionClick
        )

        if (recentCustomer.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LabelText(
                    modifier = Modifier
                        .weight(1f),
                    label = stringResource(
                        R.string.message_last_customers,
                        recentCustomer.size
                    )
                )
                TextButton(onClick = { onAllAnalysisHistoryClick() }) {
                    Text(text = stringResource(R.string.label_see_all))
                }
            }
            LazyRow(
                modifier = Modifier,
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(recentCustomer) {
                    HomeRecentCustomer(data = it) {
                        onRecentCustomerClick(it)
                    }
                }
            }
        } else {
            EmptyComponent(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.message_no_customer_or_analysis)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    CarServeTheme {
        val recentCustomer = CustomerWithVehicle(
            customer = Customer(
                "John Doe",
                "john@doe.com",
                "081311111111",
                "Jl. Address, West Java, Indonesia"
            ),
            vehicles = CustomerVehicle(
                "AG 2446 NB",
                "john@doe.com",
                "Brand 1",
                "Car Name 1",
                "Car Type 1",
                "Color 1",
                "Transmission"
            )
        )
        HomeScreen(
            greeting = "Good morning, John!",
            onServeVisionClick = {},
            recentCustomer = listOf(
                recentCustomer,
                recentCustomer,
                recentCustomer,
                recentCustomer,
            ),
            onAllAnalysisHistoryClick = {},
            onServiceAdvisorClick = {},
            onRecentCustomerClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview_Empty() {
    CarServeTheme {
        HomeScreen(
            greeting = "Good morning, John!",
            onServeVisionClick = {},
            recentCustomer = emptyList(),
            onAllAnalysisHistoryClick = {},
            onServiceAdvisorClick = {},
            onRecentCustomerClick = {}
        )
    }
}