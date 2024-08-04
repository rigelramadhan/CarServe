package one.reevdev.carserve.feature.profile.screen.input

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
import one.reevdev.carserve.core.domain.feature.profile.model.Customer
import one.reevdev.carserve.feature.common.ui.component.AppHeader
import one.reevdev.carserve.feature.profile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputCustomerRouter(
    modifier: Modifier = Modifier,
    viewModel: InputProfileViewModel = hiltViewModel(),
    onSubmit: (Customer) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCustomers()
    }

    LaunchedEffect(key1 = uiState.isPrefilled) {
        if (uiState.isPrefilled) {
            showBottomSheet = true
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeader(
                title = stringResource(R.string.label_customer_information),
                hasBackButton = true
            )
        }
    ) { innerPadding ->
        InputProfileScreen(
            modifier = Modifier
                .padding(innerPadding),
            name = uiState.param.name,
            onNameValueChange = { viewModel.setName(it) },
            email = uiState.param.email,
            onEmailValueChange = { viewModel.setEmail(it) },
            phoneNumber = uiState.param.phoneNumber,
            onPhoneNumberValueChange = { viewModel.setPhoneNumber(it) },
            address = uiState.param.address,
            onAddressValueChange = { viewModel.setAddress(it) }
        ) {
            viewModel.saveLastProfileData(uiState.param)
            onSubmit(uiState.param)
        }
    }
}