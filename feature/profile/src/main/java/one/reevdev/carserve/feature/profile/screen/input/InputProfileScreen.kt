package one.reevdev.carserve.feature.profile.screen.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.feature.common.ui.component.CarseButton
import one.reevdev.carserve.feature.common.ui.component.CarseTextField
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.feature.common.utils.isNumber
import one.reevdev.carserve.feature.common.utils.isValidEmail
import one.reevdev.carserve.feature.common.utils.isValidPhoneNumber
import one.reevdev.carserve.feature.profile.R

@Composable
fun InputProfileScreen(
    modifier: Modifier = Modifier,
    name: String,
    onNameValueChange: (String) -> Unit,
    email: String,
    onEmailValueChange: (String) -> Unit,
    phoneNumber: String,
    onPhoneNumberValueChange: (String) -> Unit,
    address: String,
    onAddressValueChange: (String) -> Unit,
    onSubmit: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 72.dp, top = 16.dp)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CarseTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = stringResource(R.string.label_name),
                        value = name,
                        onValueChange = onNameValueChange
                    )
                    CarseTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = stringResource(R.string.label_email),
                        value = email,
                        keyboardType = KeyboardType.Email,
                        onValueChange = onEmailValueChange
                    )
                    CarseTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = stringResource(R.string.label_phone_number),
                        value = phoneNumber,
                        keyboardType = KeyboardType.Phone,
                        onValueChange = {
                            if (it.isNumber()) onPhoneNumberValueChange(it)
                        }
                    )
                    CarseTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = stringResource(R.string.label_address),
                        value = address,
                        onValueChange = onAddressValueChange
                    )
                }
            }
            
            item {
                CarseButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp, top = 48.dp)
                        .align(Alignment.BottomCenter),
                    text = stringResource(R.string.action_submit),
                    enableIf = {
                        name.isNotBlank() && email.isValidEmail()
                                && phoneNumber.isValidPhoneNumber() && address.isNotBlank()
                    },
                    onClick = onSubmit
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InputProfileScreenPreview() {
    CarServeTheme {
        InputProfileScreen(
            name = "",
            onNameValueChange = {},
            email = "",
            onEmailValueChange = {},
            phoneNumber = "",
            onPhoneNumberValueChange = {},
            address = "",
            onAddressValueChange = {},
        ) {

        }
    }
}