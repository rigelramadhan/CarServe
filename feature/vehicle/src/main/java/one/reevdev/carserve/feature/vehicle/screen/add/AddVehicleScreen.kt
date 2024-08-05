package one.reevdev.carserve.feature.vehicle.screen.add

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
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle
import one.reevdev.carserve.core.domain.feature.vehicle.model.Transmission
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.common.ui.component.CarseButton
import one.reevdev.carserve.feature.common.ui.component.CarseTextField
import one.reevdev.carserve.feature.common.ui.component.SelectableTextField
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.feature.vehicle.component.SelectableList
import one.reevdev.carserve.vehicle.R

@Composable
fun AddVehicleScreen(
    modifier: Modifier = Modifier,
    vehicleChoice: List<Vehicle> = emptyList(),
    vehicle: CustomerVehicle = CustomerVehicle(),
    onProceedForm: (vehicle: CustomerVehicle) -> Unit,
) {
    val transmissionOptions = Transmission.entries.map { it.value }
    val (selectedTransmission, onTransmissionSelected) = remember {
        mutableStateOf(
            transmissionOptions[0]
        )
    }
    var policeNo by remember { mutableStateOf(emptyString()) }
    var carName by remember { mutableStateOf(emptyString()) }
    var carType by remember { mutableStateOf(emptyString()) }
    var carBrand by remember { mutableStateOf(emptyString()) }
    var color by remember { mutableStateOf(emptyString()) }
    var isFieldsDisabled by remember { mutableStateOf(false) }

    val brandChoices by remember(vehicleChoice) {
        mutableStateOf(vehicleChoice.map { it.carBrand }.distinct())
    }

    val modelChoices by remember(vehicleChoice, carBrand) {
        mutableStateOf(vehicleChoice.filter { it.carBrand == carBrand }.map { it.carName }
            .distinct())
    }

    val typeChoices by remember(vehicleChoice, carName) {
        mutableStateOf(vehicleChoice.filter { it.carName == carName }.map { it.carType }.distinct())
    }

    val colorChoices by remember(vehicleChoice, carType) {
        mutableStateOf(vehicleChoice.filter { it.carType == carType }.map { it.color }.distinct())
    }

    LaunchedEffect(vehicle) {
        vehicle.let { car ->
            policeNo = car.policeNo
            carBrand = car.carBrand
            carName = car.carName
            carType = car.carType
            color = car.color
            onTransmissionSelected(transmissionOptions.find { car.transmission == it }.orEmpty())
        }
        isFieldsDisabled = (vehicle.carBrand.isNotBlank())
    }

    Column(
        modifier = modifier
            .padding(16.dp),
    ) {
        CarseTextField(
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(R.string.label_police_number),
            value = policeNo,
            onValueChange = { policeNo = it.uppercase() }
        )
        SelectableTextField(
            modifier = Modifier
                .padding(top = 16.dp),
            selectedValue = carBrand,
            options = brandChoices,
            label = stringResource(R.string.label_car_brand),
            onValueChangedEvent = {
                carBrand = it
                carName = emptyString()
                carType = emptyString()
                color = emptyString()
                isFieldsDisabled = false
            }
        )
        SelectableTextField(
            modifier = Modifier
                .padding(top = 16.dp),
            selectedValue = carName,
            options = modelChoices,
            label = stringResource(R.string.label_car_model),
            onValueChangedEvent = {
                carName = it
                carType = emptyString()
                color = emptyString()
                isFieldsDisabled = false
            }
        )
        SelectableTextField(
            modifier = Modifier
                .padding(top = 16.dp),
            selectedValue = carType,
            options = typeChoices,
            label = stringResource(R.string.label_car_type),
            onValueChangedEvent = {
                carType = it
                when {
                    carType.contains("MT") -> {
                        onTransmissionSelected(Transmission.MANUAL.value)
                        isFieldsDisabled = true
                    }

                    carType.contains("AT") -> {
                        onTransmissionSelected(Transmission.AUTOMATIC.value)
                        isFieldsDisabled = true
                    }
                }
                color = emptyString()
            }
        )
        SelectableTextField(
            modifier = Modifier
                .padding(top = 16.dp),
            selectedValue = color,
            options = colorChoices,
            label = stringResource(R.string.label_car_color),
            onValueChangedEvent = {
                color = it
            }
        )
        SelectableList(
            modifier = Modifier
                .padding(top = 32.dp),
            label = stringResource(id = R.string.label_transmission),
            options = transmissionOptions,
            selected = selectedTransmission,
            enableIf = { !isFieldsDisabled }
        ) {
            onTransmissionSelected(it)
        }
        CarseButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            text = stringResource(R.string.label_proceed),
            enableIf = {
                carName.isNotBlank() && color.isNotBlank() && selectedTransmission.isNotBlank() && policeNo.isNotBlank()
            },
            onClick = {
                val submitVehicle = vehicle.copy(
                    policeNo = policeNo,
                    carBrand = carBrand,
                    carName = carName,
                    carType = carType,
                    color = color,
                    transmission = selectedTransmission
                )
                onProceedForm(
                    if (submitVehicle == vehicle) submitVehicle
                    else submitVehicle.copy(policeNo = policeNo)
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