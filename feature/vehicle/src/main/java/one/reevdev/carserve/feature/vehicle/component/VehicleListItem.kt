package one.reevdev.carserve.feature.vehicle.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme

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
        contentPadding = PaddingValues(bottom = 8.dp)
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
            VehicleItem(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp),
                vehicle = vehicle,
                onChooseVehicle = onChooseOption
            )
        }

        contentBelow(this)
    }
}

@Composable
fun VehicleItem(
    modifier: Modifier = Modifier,
    vehicle: Vehicle,
    onChooseVehicle: (Vehicle) -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .clickable { onChooseVehicle(vehicle) },
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = vehicle.carType,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
            )
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.End
            ) {
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

@Preview(showBackground = true)
@Composable
private fun VehicleListItemPreview() {
    CarServeTheme {
        VehicleListItem(
            vehicleList = listOf(
                Vehicle(1, "Daihatsu", "Terios", "Daihatsu Terios 2022", "White", "Automatic"),
                Vehicle(1, "Daihatsu", "Terios", "Daihatsu Xenia 2022", "Black", "Manual"),
                Vehicle(1, "Daihatsu", "Terios", "Daihatsu Sigra 2022", "Red", "Manual"),
                Vehicle(1, "Daihatsu", "Terios", "Daihatsu Agya 2022", "White", "Automatic"),
            ),
            onChooseOption = {

            }
        )
    }
}