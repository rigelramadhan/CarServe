package one.reevdev.carserve.feature.service.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.common.ui.component.TextWithLabel
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.feature.service.R

@Composable
fun ServiceHistoryItem(
    modifier: Modifier = Modifier,
    customer: String?,
    vehicle: Vehicle?,
    findingCount: Int,
    onItemClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(18.dp)
    ) {
        vehicle?.run {
//            Text(text = customer, style = MaterialTheme.typography.titleLarge)
//            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            CardColumn(
                modifier = Modifier.padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                padding = 12.dp
            ) {
                Text(
                    text = stringResource(
                        R.string.template_vehicle_title,
                        carName,
                        color,
                        transmission
                    ),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            customer?.let {
                TextWithLabel(
                    modifier = Modifier
                        .padding(bottom = 12.dp),
                    label = "Customer Email",
                    text = customer
                )
            }
            TextWithLabel(
                modifier = Modifier,
                label = stringResource(R.string.label_findings),
                text = stringResource(R.string.message_result_finding_count, findingCount)
            )
        }
    }
}

@Preview
@Composable
private fun ServiceHistoryItemPreview() {
    CarServeTheme {
        ServiceHistoryItem(
            customer = "John Doe",
            vehicle = Vehicle(
                id = 0,
                carName = "Daihatsu Terios R AT 2021",
                color = "Black",
                transmission = "Automatic"
            ),
            findingCount = 4,
        ) {}
    }
}