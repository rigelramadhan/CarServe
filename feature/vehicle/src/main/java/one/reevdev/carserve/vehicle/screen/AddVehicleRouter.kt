package one.reevdev.carserve.vehicle.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.common.ui.component.AppHeader
import one.reevdev.carserve.vehicle.R

@Composable
fun AddVehicleRouter(
    modifier: Modifier = Modifier,
    onProceedForm: (param: Vehicle) -> Unit,
    viewModel: AddVehicleViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllVehicle()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeader(
                title = stringResource(R.string.title_add_vehicle)
            )
        }
    ) { innerPadding ->
        AddVehicleScreen(
            modifier = Modifier
                .padding(innerPadding),
            onProceedForm = onProceedForm
        )
    }
}