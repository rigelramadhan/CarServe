package one.reevdev.carserve.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.R
import one.reevdev.carserve.ui.component.HomeCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onServeVisionClick: () -> Unit,
    onMyVehicleClick: () -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                HomeCard(
                    title = stringResource(R.string.title_serve_vision),
                    description = stringResource(R.string.description_serve_vision),
                    icon = R.drawable.ic_verified_24,
                    onClick = onServeVisionClick
                )
                HomeCard(
                    title = stringResource(R.string.title_my_vehicles),
                    description = stringResource(R.string.description_my_vehicle),
                    onClick = onMyVehicleClick
                )
            }
        }
    }
}