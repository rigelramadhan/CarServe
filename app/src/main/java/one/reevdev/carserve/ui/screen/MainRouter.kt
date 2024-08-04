package one.reevdev.carserve.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import one.reevdev.carserve.R
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle
import one.reevdev.carserve.feature.common.ui.component.ConfirmationDialog
import one.reevdev.carserve.feature.common.ui.navigation.Route
import one.reevdev.carserve.feature.service.navigation.routes.AnalysisRoutes
import one.reevdev.carserve.feature.service.navigation.serviceRouter
import one.reevdev.carserve.feature.vehicle.navigation.VehicleRoutes
import one.reevdev.carserve.feature.vehicle.navigation.navigateToVehicle
import one.reevdev.carserve.feature.vehicle.navigation.vehicleRouter
import one.reevdev.carserve.ui.component.BottomNavBar
import one.reevdev.carserve.ui.navigation.MainRoutes
import one.reevdev.carserve.ui.navigation.homeScreen
import one.reevdev.carserve.utils.BottomNavBarData

@Composable
fun MainRouter(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    startDestination: Any = MainRoutes.Home,
    navController: NavHostController = rememberNavController(),
    navigateToService: (CustomerVehicle) -> Unit,
    navigateToAnalysisDetail: (ServiceAnalysis) -> Unit,
    navigateToAnalysisHistory: () -> Unit,
    navigateToServiceAdvisor: () -> Unit,
    onLoggedOut: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var showLogoutConfirmationDialog by remember { mutableStateOf(false) }

    val bottomBarItems = remember {
        listOf(
            BottomNavBarData(
                route = MainRoutes.Home,
                label = R.string.home,
                icon = R.drawable.ic_home_24
            ),

            BottomNavBarData(
                route = { navigateToAnalysisHistory() },
                label = R.string.label_history,
                icon = R.drawable.ic_history_24
            ),

            BottomNavBarData(
                route = VehicleRoutes.Vehicle,
                label = R.string.label_my_vehicle,
                icon = R.drawable.ic_airport_shuttle_24
            ),

            BottomNavBarData(
                route = {
                    showLogoutConfirmationDialog = true
                },
                label = R.string.label_logout,
                icon = R.drawable.ic_logout_24
            ),
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
                items = bottomBarItems,
                currentRoute = currentRoute.orEmpty(),
                onItemSelect = {
                    when (val route = it) {
                        is Route -> {
                            navController.navigate(route)
                        }

                        is Function0<*> -> {
                            @Suppress("UNCHECKED_CAST")
                            (route as? () -> Unit)?.invoke()
                        }

                        else -> Unit
                    }
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
                onServeVisionClick = { navigateToService(CustomerVehicle()) },
                onMyVehicleClick = { navController.navigateToVehicle() },
                onAllAnalysisHistoryClick = { navigateToAnalysisHistory() },
                onAnalysisHistoryItemClick = { navigateToAnalysisDetail(it) },
                onServiceAdvisorClick = { navigateToServiceAdvisor() },
                onRecentCustomerClick = {  }
            )
            vehicleRouter(
                onAnalyzeVehicle = {
                    selectedItem = 1
                    navigateToService(it)
                }
            )
            serviceRouter(AnalysisRoutes.AnalysisHistory) {}
        }
    }

    if (showLogoutConfirmationDialog) {
        ConfirmationDialog(
            title = stringResource(R.string.message_logout_confirmation),
            positiveButtonText = stringResource(R.string.yes),
            onPositiveAction = { viewModel.logout() },
            negativeButtonText = stringResource(R.string.no),
            onNegativeAction = {
                showLogoutConfirmationDialog = false
            },
            onDismissRequest = {
                showLogoutConfirmationDialog = false
            }
        )
    }
}