package one.reevdev.carserve.vehicle.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.core.domain.feature.vehicle.model.Transmission
import one.reevdev.carserve.core.domain.feature.vehicle.model.VehicleParam
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.vehicle.R
import one.reevdev.carserve.vehicle.component.SelectableList

@Composable
fun AddVehicleScreen(
    modifier: Modifier = Modifier,
    onProceedForm: (param: VehicleParam) -> Unit,
) {
    val transmissionOptions by remember { mutableStateOf(Transmission.entries.map { it.value }) }
    val (selected, onSelected) = remember { mutableStateOf(transmissionOptions[0]) }
    var carName by rememberSaveable { mutableStateOf(emptyString()) }
    var color by rememberSaveable { mutableStateOf(emptyString()) }

    Column(
        modifier = modifier
            .padding(16.dp),
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = carName,
            onValueChange = { carName = it },
            label = { Text(text = stringResource(R.string.label_car_model)) },
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            value = color,
            onValueChange = { color = it },
            label = { Text(text = stringResource(R.string.label_car_color)) },
            shape = RoundedCornerShape(16.dp)
        )
        SelectableList(
            modifier = Modifier
                .padding(top = 48.dp),
            label = stringResource(id = R.string.label_transmission),
            options = transmissionOptions,
            selected = selected
        ) {
            onSelected(it)
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            onClick = { onProceedForm(VehicleParam(carName, color, selected)) }
        ) {
            Text(text = stringResource(R.string.label_proceed))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddVehicleScreenPreview() {
    CarServeTheme {
        AddVehicleScreen {}
    }
}