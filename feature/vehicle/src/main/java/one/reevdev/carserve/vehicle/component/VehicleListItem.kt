package one.reevdev.carserve.vehicle.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle

@Composable
fun VehicleListItem(
    modifier: Modifier = Modifier,
    title: String? = null,
    vehicleList: List<Vehicle>,
    contentAbove: (LazyListScope) -> Unit = {},
    contentBelow: (LazyListScope) -> Unit = {},
    onChooseOption: (Vehicle?) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .padding(bottom = 48.dp),
    ) {
        contentAbove(this)

        title?.let {
            item {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = title
                )
            }
        }
        items(vehicleList) { vehicle ->
            OutlinedCard(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
                    .clickable { onChooseOption(vehicle) },
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = vehicle.carName,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                    )
                    Column {
                        Text(
                            modifier = Modifier,
                            text = vehicle.transmission,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            modifier = Modifier,
                            text = vehicle.color,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        contentBelow(this)
    }
}