package one.reevdev.carserve.feature.vehicle.screen.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.common.ui.component.ConfirmationDialog
import one.reevdev.carserve.feature.common.ui.component.EmptyComponent
import one.reevdev.carserve.feature.vehicle.component.VehicleListItem
import one.reevdev.carserve.vehicle.R

@Composable
fun VehicleListScreen(
    modifier: Modifier = Modifier,
    vehicles: List<Vehicle> = emptyList(),
    onVehicleDelete: (id: Int) -> Unit,
    onAnalyzeVehicle: (Vehicle) -> Unit,
) {
    var showConfirmationDialog by remember { mutableStateOf<Vehicle?>(null) }

    if (vehicles.isNotEmpty()) {
        VehicleListItem(
            modifier = modifier,
            vehicleList = vehicles,
            onChooseOption = { vehicle ->
                vehicle?.let {
                    showConfirmationDialog = it
                }
            }
        )
    } else {
        EmptyComponent(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.message_no_vehicle_found)
        )
    }

    showConfirmationDialog?.run {
        ConfirmationDialog(
            message = stringResource(
                R.string.message_vehicle_confirmation_dialog,
                carName,
                color,
                transmission
            ),
            negativeButtonText = stringResource(R.string.action_delete),
            onNegativeAction = {
                onVehicleDelete(id)
                showConfirmationDialog = null
            },
            positiveButtonText = stringResource(R.string.action_analyze),
            onPositiveAction = {
                onAnalyzeVehicle(this)
                showConfirmationDialog = null
            }
        )
    }
}