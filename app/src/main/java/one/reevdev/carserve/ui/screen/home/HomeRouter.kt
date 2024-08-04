package one.reevdev.carserve.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerWithVehicle
import one.reevdev.carserve.feature.common.ui.component.AppHeader
import one.reevdev.carserve.feature.service.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRouter(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onServeVisionClick: () -> Unit,
    onMyVehicleClick: () -> Unit,
    onAllAnalysisHistoryClick: () -> Unit,
    onAnalysisHistoryItemClick: (ServiceAnalysis) -> Unit,
    onServiceAdvisorClick: () -> Unit,
    onPhoneClick: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showCustomerBottomSheet by remember { mutableStateOf<CustomerWithVehicle?>(null) }

    LaunchedEffect(key1 = Unit) {
        viewModel.getRecentCustomers()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeader()
        }
    ) { innerPadding ->
        HomeScreen(
            modifier = Modifier
                .padding(innerPadding),
            greeting = "Good morning!",
            recentCustomer = uiState.recentCustomers,
            onServeVisionClick = onServeVisionClick,
            onAllAnalysisHistoryClick = onAllAnalysisHistoryClick,
            onServiceAdvisorClick = onServiceAdvisorClick,
            onRecentCustomerClick = {
                showCustomerBottomSheet = it
            }
        )

        if (showCustomerBottomSheet != null) {
            ModalBottomSheet(
                onDismissRequest = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showCustomerBottomSheet = null
                        }
                    }
                },
                sheetState = sheetState
            ) {
                RecentCustomerDetail(
                    vehicle = showCustomerBottomSheet?.vehicles ?: CustomerVehicle(),
                    customerName = showCustomerBottomSheet?.customer?.name.orEmpty(),
                    customerPhoneNo = showCustomerBottomSheet?.customer?.phoneNumber.orEmpty(),
                    onPhoneClick = { onPhoneClick(showCustomerBottomSheet?.customer?.phoneNumber.orEmpty()) }
                )
            }
        }
    }
}

@Composable
fun RecentCustomerDetail(
    modifier: Modifier = Modifier,
    vehicle: CustomerVehicle,
    customerName: String,
    customerPhoneNo: String,
    onPhoneClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                painter = painterResource(id = one.reevdev.carserve.feature.common.R.drawable.placeholder_customer_avatar),
                contentDescription = null,
            )
            Column(
                Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier,
                    text = customerName,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    modifier = Modifier,
                    text = customerPhoneNo,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.5f
                        )
                    )
                )
            }
            IconButton(
                onClick = onPhoneClick,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Call,
                    contentDescription = stringResource(R.string.content_description_call_customer)
                )
            }
        }
        vehicle.run {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                text = carType,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 32.dp),
                text = stringResource(
                    R.string.format_policeno_color_transmission,
                    policeNo,
                    color,
                    transmission
                ),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}