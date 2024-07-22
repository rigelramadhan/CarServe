package one.reevdev.carserve.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import one.reevdev.carserve.feature.common.ui.component.AppHeader

@Composable
fun HomeRouter(
    modifier: Modifier = Modifier,
    onServeVisionClick: () -> Unit,
    onMyVehicleClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeader()
        }
    ) { innerPadding ->
        HomeScreen(
            modifier = Modifier
                .padding(innerPadding),
            onServeVisionClick = onServeVisionClick,
            onMyVehicleClick = onMyVehicleClick,
        )
    }
}