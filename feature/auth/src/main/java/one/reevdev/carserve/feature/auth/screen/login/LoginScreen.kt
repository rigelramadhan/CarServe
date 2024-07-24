package one.reevdev.carserve.feature.auth.screen.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.feature.auth.R
import one.reevdev.carserve.feature.common.ui.component.CarseButton
import one.reevdev.carserve.feature.common.ui.component.CarseTextField
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.feature.common.utils.isValidEmail

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    showPassword: Boolean,
    onShowPasswordClick: () -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        CarseTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(R.string.email),
            keyboardType = KeyboardType.Email,
            value = email,
            onValueChange = onEmailChange
        )
        CarseTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            label = stringResource(R.string.password),
            endButton = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
            onEndButtonClick = onShowPasswordClick,
            keyboardType = KeyboardType.Password,
            isValueVisible = showPassword,
            value = password,
            onValueChange = onPasswordChange
        )
        CarseButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            text = stringResource(R.string.login),
            enableIf = {
                email.isValidEmail() && password.length >= 8
            },
            onClick = onLoginClick
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
        )
        CarseButton(
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            ),
            text = stringResource(R.string.register),
            onClick = onRegisterClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    CarServeTheme {
        LoginScreen(
            email = "rigeltest@email.com",
            onEmailChange = {},
            password = "testingsatuduatiga",
            onPasswordChange = {},
            showPassword = false,
            onShowPasswordClick = {},
            onLoginClick = {},
            onRegisterClick = {}
        )
    }
}