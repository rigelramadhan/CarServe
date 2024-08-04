package one.reevdev.carserve.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
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
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.feature.common.R as CommonRes

@Composable
fun HomeRecentCustomer(
    modifier: Modifier = Modifier,
    data: CustomerWithVehicle,
    onClick: () -> Unit,
) {
    OutlinedCard(
        modifier = modifier
            .width(170.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = CommonRes.drawable.placeholder_customer_avatar),
                    contentDescription = null,
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = data.customer.name,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                modifier = Modifier,
                text = stringResource(
                    R.string.format_car_name_color,
                    data.vehicles.carName,
                    data.vehicles.color
                ),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier
                    .padding(top = 4.dp),
                text = data.vehicles.policeNo,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
private fun HomeRecentCustomerPreview() {
    CarServeTheme {
        HomeRecentCustomer(
            data = CustomerWithVehicle(
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
        ) {}
    }
}