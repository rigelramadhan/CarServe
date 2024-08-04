package one.reevdev.carserve.feature.service.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle
import one.reevdev.carserve.feature.common.ui.component.LabelText
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.feature.service.R

@Composable
fun ServiceHistoryItem(
    modifier: Modifier = Modifier,
    customerName: String,
    customerPhoneNo: String,
    vehicle: CustomerVehicle,
    findingCount: Int,
    estimatedPrice: String,
    onItemClick: () -> Unit,
    onPhoneClick: () -> Unit,
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        onClick = onItemClick
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            vehicle.run {
                Text(
                    modifier = Modifier,
                    text = carName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    modifier = Modifier,
                    text = stringResource(
                        R.string.format_policeno_color_transmission,
                        policeNo,
                        color,
                        transmission
                    ),
                    style = MaterialTheme.typography.bodySmall
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = one.reevdev.carserve.feature.common.R.drawable.placeholder_customer_avatar),
                    contentDescription = null,
                )
                Column(
                    Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        modifier = Modifier,
                        text = customerName,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        modifier = Modifier,
                        text = customerPhoneNo,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.5f
                            )
                        )
                    )
                }
                IconButton(
                    onClick = onPhoneClick,
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Call,
                        contentDescription = stringResource(R.string.content_description_call_customer)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Column {
                    LabelText(
                        style = MaterialTheme.typography.bodySmall,
                        label = stringResource(id = R.string.label_estimated_price)
                    )
                    LabelText(
                        label = estimatedPrice,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Column {
                    LabelText(
                        style = MaterialTheme.typography.bodySmall,
                        label = stringResource(id = R.string.label_findings)
                    )
                    LabelText(
                        label = findingCount.toString(),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ServiceHistoryItemPreview() {
    CarServeTheme {
        ServiceHistoryItem(
            customerName = "John Doe",
            customerPhoneNo = "081311119922",
            vehicle = CustomerVehicle(
                policeNo = "AG 2446 NB",
                ownerEmail = "john@doe.com",
                carBrand = "Brand 1",
                carName = "Car Name 1",
                color = "Color 1",
                carType = "Car Type 1",
                transmission = "Transmission"
            ),
            estimatedPrice = "Rp3,500,000.00",
            findingCount = 4,
            onItemClick = {}
        ) {}
    }
}