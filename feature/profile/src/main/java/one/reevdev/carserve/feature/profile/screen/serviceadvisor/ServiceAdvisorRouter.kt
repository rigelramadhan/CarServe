package one.reevdev.carserve.feature.profile.screen.serviceadvisor

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
fun ServiceAdvisorRouter(
    modifier: Modifier = Modifier,
    viewModel: ServiceAdvisorViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.getServiceAdvisorData()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeader(
                title = stringResource(R.string.title_service_advisor_information),
                hasBackButton = true
            )
        }
    ) { innerPadding ->
        ServiceAdvisorScreen(
            modifier = Modifier.padding(innerPadding),
            username = uiState.username,
            email = uiState.email,
        )
    }

    if (uiState.loadingState != LoadingState.NotLoading) {
        LoadingDialog()
    }
}