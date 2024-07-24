package one.reevdev.carserve.feature.profile.screen.input

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.core.domain.feature.profile.model.ProfileParam
import one.reevdev.carserve.feature.profile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputProfileRouter(
    modifier: Modifier = Modifier,
    viewModel: InputProfileViewModel = hiltViewModel(),
    onSubmit: (ProfileParam) -> Unit,
) {
    val (name, onNameValueChange) = rememberSaveable { mutableStateOf(emptyString()) }
    val (email, onEmailValueChange) = rememberSaveable { mutableStateOf(emptyString()) }
    val (phoneNumber, onPhoneNumberValueChange) = rememberSaveable { mutableStateOf(emptyString()) }
    val (address, onAddressValueChange) = rememberSaveable { mutableStateOf(emptyString()) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.label_profile)) })
        }
    ) { innerPadding ->
        InputProfileScreen(
            modifier = Modifier
                .padding(innerPadding),
            name = name,
            onNameValueChange = onNameValueChange,
            email = email,
            onEmailValueChange = onEmailValueChange,
            phoneNumber = phoneNumber,
            onPhoneNumberValueChange = onPhoneNumberValueChange,
            address = address,
            onAddressValueChange = onAddressValueChange
        ) {
            val param = ProfileParam(name, email, phoneNumber, address)
            onSubmit(param)
        }
    }
}