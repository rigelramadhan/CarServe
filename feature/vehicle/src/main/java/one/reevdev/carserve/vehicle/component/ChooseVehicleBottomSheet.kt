package one.reevdev.carserve.vehicle.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.common.ui.component.EmptyComponent
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.vehicle.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseVehicleBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    vehicleList: List<Vehicle> = emptyList(),
    onDismiss: () -> Unit,
    onChooseOption: (Vehicle?) -> Unit,
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        if (vehicleList.isNotEmpty()) {
            ChooseCarItem(vehicleList = vehicleList, onChooseOption = onChooseOption)
        } else {
            EmptyComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                text = stringResource(R.string.message_no_vehicle_found)
            )
        }
    }
}

@Composable
fun ChooseCarItem(
    modifier: Modifier = Modifier,
    vehicleList: List<Vehicle>,
    onChooseOption: (Vehicle?) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .padding(bottom = 48.dp),
    ) {
        item {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = stringResource(R.string.title_choose_your_vehicle)
            )
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
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun ChooseVehicleBottomSheetPreview() {
    CarServeTheme {
        ChooseVehicleBottomSheet(
            sheetState = SheetState(
                true,
                density = LocalDensity.current,
                initialValue = SheetValue.Expanded
            ),
            vehicleList = listOf(
                Vehicle(1, "Car 1", "Black", "Automatic"),
                Vehicle(1, "Car 1", "Black", "Automatic"),
                Vehicle(1, "Car 1", "Black", "Automatic"),
                Vehicle(1, "Car 1", "Black", "Automatic"),
            ),
            onDismiss = {}
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun ChooseVehicleBottomSheetPreview_Empty() {
    CarServeTheme {
        ChooseVehicleBottomSheet(
            sheetState = SheetState(
                true,
                density = LocalDensity.current,
                initialValue = SheetValue.Expanded
            ),
            vehicleList = emptyList(),
            onDismiss = {}
        ) {

        }
    }
}