package one.reevdev.carserve.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import one.reevdev.carserve.R
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.vehicle.navigation.navigateToVehicle
import one.reevdev.carserve.feature.vehicle.navigation.vehicleRouter
import one.reevdev.carserve.ui.component.BottomNavBar
import one.reevdev.carserve.ui.navigation.MainRoutes
import one.reevdev.carserve.ui.navigation.homeScreen
import one.reevdev.carserve.ui.navigation.navigateToHome
import one.reevdev.carserve.utils.BottomNavBarData

@Composable
fun MainRouter(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    startDestination: Any = MainRoutes.Home,
    navController: NavHostController = rememberNavController(),
    navigateToService: (Vehicle) -> Unit,
    onLoggedOut: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val bottomBarItems = remember {
        mapOf(
            0 to BottomNavBarData(
                label = R.string.home, icon = R.drawable.ic_home_24
            ) { navController.navigateToHome() },

            1 to BottomNavBarData(
                label = R.string.label_my_vehicle, icon = R.drawable.ic_airport_shuttle_24
            ) { navController.navigateToVehicle() },

            2 to BottomNavBarData(
                label = R.string.label_logout, icon = R.drawable.ic_logout_24
            ) { viewModel.logout() },
        )
    }
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    LaunchedEffect(key1 = uiState.hasLoggedOut) {
        if (uiState.hasLoggedOut) {
            onLoggedOut()
        }
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavBar(
                items = bottomBarItems.values.toList(),
                selected = selectedItem,
                onItemSelect = {
                    selectedItem = it
                    bottomBarItems[it]?.navigateAction?.invoke()
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .padding(innerPadding),
            navController = navController,
            startDestination = startDestination
        ) {
            homeScreen(
                onServeVisionClick = { navigateToService(Vehicle()) },
                onMyVehicleClick = { navController.navigateToVehicle() }
            )
            vehicleRouter(
                onAnalyzeVehicle = {
                    selectedItem = 1
                    navigateToService(it)
                }
            )
        }
    }
}