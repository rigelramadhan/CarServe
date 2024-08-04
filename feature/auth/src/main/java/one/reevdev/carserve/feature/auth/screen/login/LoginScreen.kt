package one.reevdev.carserve.feature.auth.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
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
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.surfaceVariant
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .width(264.dp),
                painter = painterResource(id = R.drawable.login_illustration),
                contentDescription = stringResource(R.string.content_description_login_illustrator)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(8.dp),
                painter = painterResource(id = R.drawable.drag_handle),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                text = stringResource(R.string.message_login)
            )
            CarseTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
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
                    .padding(top = 48.dp)
                    .padding(bottom = 32.dp),
                text = stringResource(R.string.login),
                enableIf = {
                    email.isValidEmail() && password.length >= 8
                },
                onClick = onLoginClick
            )
//        HorizontalDivider(
//            modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
//        )
//        CarseButton(
//            modifier = Modifier
//                .fillMaxWidth(),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = MaterialTheme.colorScheme.tertiary,
//                contentColor = MaterialTheme.colorScheme.onTertiary
//            ),
//            text = stringResource(R.string.register),
//            onClick = onRegisterClick
//        )
        }
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