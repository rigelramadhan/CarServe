package one.reevdev.carserve.feature.vehicle.screen.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle
import one.reevdev.carserve.feature.common.ui.component.ConfirmationDialog
import one.reevdev.carserve.feature.common.ui.component.EmptyComponent
import one.reevdev.carserve.feature.vehicle.component.VehicleListItem
import one.reevdev.carserve.vehicle.R

@Composable
fun VehicleListScreen(
    modifier: Modifier = Modifier,
    vehicles: List<CustomerVehicle> = emptyList(),
    onAddVehicle: () -> Unit,
    onVehicleDelete: (policeNo: String) -> Unit,
    onAnalyzeVehicle: (CustomerVehicle) -> Unit,
) {
    var confirmationDialogData by remember { mutableStateOf<CustomerVehicle?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        if (vehicles.isNotEmpty()) {
            VehicleListItem(
                modifier = Modifier,
                vehicleList = vehicles,
                onChooseOption = { vehicle ->
                    vehicle?.let {
                        confirmationDialogData = it
                    }
                }
            )
        } else {
            EmptyComponent(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.message_no_vehicle_found)
            )
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp),
            onClick = onAddVehicle
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.content_description_add_vehicle_icon)
            )
        }
    }

    confirmationDialogData?.run {
        ConfirmationDialog(
            title = "What to do with this vehicle?",
            message = stringResource(
                R.string.message_vehicle_confirmation_dialog,
                carName,
                color,
                transmission
            ),
            negativeButtonText = stringResource(R.string.action_delete),
            onNegativeAction = {
                onVehicleDelete(policeNo)
                confirmationDialogData = null
            },
            positiveButtonText = stringResource(R.string.action_analyze),
            onPositiveAction = {
                onAnalyzeVehicle(this)
                confirmationDialogData = null
            },
            onDismissRequest = {
                confirmationDialogData = null
            }
        )
    }
}