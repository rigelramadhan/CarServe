package one.reevdev.carserve.feature.profile.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.feature.profile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginRouter(
    modifier: Modifier = Modifier,
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val (email, onEmailChange) = remember { mutableStateOf(emptyString()) }
    val (password, onPasswordChange) = remember { mutableStateOf(emptyString()) }
    val (showPassword, onShowPasswordClick) = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.login)) })
        }
    ) { innerPadding ->
        LoginScreen(
            modifier = Modifier
                .padding(innerPadding),
            email = email,
            onEmailChange = onEmailChange,
            password = password,
            onPasswordChange = onPasswordChange,
            showPassword = showPassword,
            onShowPasswordClick = {
                onShowPasswordClick(!showPassword)
            },
            onLoginClick = {
                navigateToHome()
            },
            onRegisterClick = navigateToRegister
        )
    }
}