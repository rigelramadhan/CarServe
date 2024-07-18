package one.reevdev.carserve.vehicle.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.core.domain.feature.vehicle.model.Transmission
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.common.ui.component.CarseButton
import one.reevdev.carserve.feature.common.ui.component.CarseTextField
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.vehicle.R
import one.reevdev.carserve.vehicle.component.SelectableList

@Composable
fun AddVehicleScreen(
    modifier: Modifier = Modifier,
    vehicle: Vehicle? = null,
    onProceedForm: (vehicle: Vehicle) -> Unit,
) {
    val transmissionOptions by remember { mutableStateOf(Transmission.entries.map { it.value }) }
    val (selected, onSelected) = remember { mutableStateOf(transmissionOptions[0]) }
    var carName by remember { mutableStateOf(emptyString()) }
    var color by remember { mutableStateOf(emptyString()) }
    var isFieldsDisabled by remember { mutableStateOf(false) }
    
    LaunchedEffect(vehicle) {
        vehicle?.let { car ->
            carName = car.carName
            color = car.color
            onSelected(transmissionOptions.find { car.transmission == it }.orEmpty())
        }
        isFieldsDisabled = (vehicle != null)
    }

    Column(
        modifier = modifier
            .padding(16.dp),
    ) {
        CarseTextField(
            modifier = Modifier.fillMaxWidth(),
            value = carName,
            onValueChange = { carName = it },
            enabledIf = { !isFieldsDisabled },
            label = stringResource(R.string.label_car_model),
        )
        CarseTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            value = color,
            onValueChange = { color = it },
            enabledIf = { !isFieldsDisabled },
            label = stringResource(R.string.label_car_color),
        )
        SelectableList(
            modifier = Modifier
                .padding(top = 48.dp),
            label = stringResource(id = R.string.label_transmission),
            options = transmissionOptions,
            selected = selected,
            enableIf = { !isFieldsDisabled }
        ) {
            onSelected(it)
        }
        CarseButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            text = stringResource(R.string.label_proceed),
            enableIf = {
                carName.isNotBlank() && color.isNotBlank() && selected.isNotBlank()
            },
            onClick = {
                onProceedForm(
                    Vehicle(
                        carName = carName,
                        color = color,
                        transmission = selected
                    )
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddVehicleScreenPreview() {
    CarServeTheme {
        AddVehicleScreen {}
    }
}