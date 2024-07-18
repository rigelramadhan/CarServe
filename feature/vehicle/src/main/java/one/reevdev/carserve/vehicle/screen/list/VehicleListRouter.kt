package one.reevdev.carserve.vehicle.screen.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle

@Composable
fun VehicleListRouter(
    modifier: Modifier = Modifier,
    viewModel: VehicleListViewModel = hiltViewModel(),
    onAnalyzeVehicle: (Vehicle) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        VehicleListScreen(
            Modifier
                .padding(innerPadding),
            vehicles = uiState.vehicles,
            onVehicleDelete = {
                viewModel.deleteVehicle(it)
            },
            onAnalyzeVehicle = onAnalyzeVehicle
        )
    }
}