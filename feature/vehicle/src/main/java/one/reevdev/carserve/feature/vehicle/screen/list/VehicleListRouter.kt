package one.reevdev.carserve.feature.vehicle.screen.list

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
fun VehicleListRouter(
    modifier: Modifier = Modifier,
    viewModel: VehicleListViewModel = hiltViewModel(),
    onAddVehicle: () -> Unit,
    onAnalyzeVehicle: (Vehicle) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAllVehicle()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeader(
                title = stringResource(R.string.title_my_vehicles),
            )
        }
    ) { innerPadding ->
        VehicleListScreen(
            Modifier
                .padding(innerPadding),
            vehicles = uiState.vehicles,
            onAddVehicle = onAddVehicle,
            onVehicleDelete = {
                viewModel.deleteVehicle(it)
            },
            onAnalyzeVehicle = onAnalyzeVehicle
        )
    }
}