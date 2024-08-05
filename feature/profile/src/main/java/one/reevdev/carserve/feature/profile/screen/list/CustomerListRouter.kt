package one.reevdev.carserve.feature.profile.screen.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import one.reevdev.carserve.feature.common.ui.component.AppHeader
import one.reevdev.carserve.feature.common.ui.component.LoadingDialog
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import one.reevdev.carserve.feature.profile.R

@Composable
fun CustomerListRouter(
    modifier: Modifier = Modifier,
    viewModel: CustomerListViewModel = hiltViewModel(),
    onCustomerClick: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCustomers()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeader(title = stringResource(R.string.title_customers), hasBackButton = true)
        }
    ) { innerPadding ->
        CustomerListScreen(
            modifier = Modifier
                .padding(innerPadding),
            customers = uiState.customers,
            onClick = onCustomerClick
        )
    }

    if (uiState.loadingState != LoadingState.NotLoading) {
        LoadingDialog()
    }
}