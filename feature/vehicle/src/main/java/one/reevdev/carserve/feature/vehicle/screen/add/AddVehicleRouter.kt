package one.reevdev.carserve.feature.vehicle.screen.add

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle
import one.reevdev.carserve.feature.common.ui.component.AppHeader
import one.reevdev.carserve.feature.vehicle.component.ChooseVehicleBottomSheet
import one.reevdev.carserve.vehicle.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddVehicleRouter(
    modifier: Modifier = Modifier,
    shouldShowCarOptions: Boolean = false,
    viewModel: AddVehicleViewModel = hiltViewModel(),
    onSubmitVehicle: (vehicle: CustomerVehicle) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var vehicle by remember { mutableStateOf(CustomerVehicle()) }

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(shouldShowCarOptions) }

    LaunchedEffect(key1 = Unit) {
        if (shouldShowCarOptions) viewModel.getAllCustomerVehicle()
        viewModel.getAllVehicle()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeader(
                title = stringResource(R.string.title_add_vehicle),
                hasBackButton = true
            )
        }
    ) { innerPadding ->
        AddVehicleScreen(
            modifier = Modifier
                .padding(innerPadding),
            onProceedForm = { vehicle ->
                viewModel.saveVehicle(vehicle)
                onSubmitVehicle(vehicle)
            },
            vehicleChoice = uiState.vehicleChoices,
            vehicle = vehicle,
        )
        if (showBottomSheet) {
            ChooseVehicleBottomSheet(
                sheetState = sheetState,
                vehicleList = uiState.lastCustomerVehicle,
                onDismiss = { showBottomSheet = false }
            ) { chosenVehicle ->
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet = false
                    }
                }
                vehicle = chosenVehicle ?: CustomerVehicle()
            }
        }
    }
}