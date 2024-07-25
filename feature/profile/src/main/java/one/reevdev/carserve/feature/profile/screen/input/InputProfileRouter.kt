package one.reevdev.carserve.feature.profile.screen.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import one.reevdev.carserve.core.domain.feature.profile.model.SavedProfile
import one.reevdev.carserve.feature.common.ui.component.AppHeader
import one.reevdev.carserve.feature.common.ui.component.CarseButton
import one.reevdev.carserve.feature.common.ui.component.OutlinedCarseButton
import one.reevdev.carserve.feature.profile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputProfileRouter(
    modifier: Modifier = Modifier,
    viewModel: InputProfileViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    onSubmit: (SavedProfile) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        viewModel.getLastProfileData()
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
                hasBackButton = true,
                navigateBack = navigateBack
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

        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier,
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp, bottom = 32.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(
                            R.string.message_prefill_last_input,
                            uiState.param.email
                        ),
                        textAlign = TextAlign.Center
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        fun closeBottomSheet() {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        }
                        OutlinedCarseButton(
                            modifier = Modifier
                                .weight(1f),
                            text = stringResource(R.string.no)
                        ) {
                            viewModel.removePrefilledData()
                            closeBottomSheet()
                        }
                        CarseButton(
                            modifier = Modifier
                                .weight(1f),
                            text = stringResource(R.string.yes)
                        ) {
                            closeBottomSheet()
                        }
                    }
                }
            }
        }
    }
}