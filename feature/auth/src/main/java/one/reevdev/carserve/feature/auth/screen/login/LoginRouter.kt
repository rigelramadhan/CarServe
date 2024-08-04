package one.reevdev.carserve.feature.auth.screen.login

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.feature.auth.R
import one.reevdev.carserve.feature.common.ui.component.AppHeader
import one.reevdev.carserve.feature.common.ui.component.LoadingDialog
import one.reevdev.carserve.feature.common.ui.state.LoadingState

@Composable
fun LoginRouter(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val (email, onEmailChange) = remember { mutableStateOf(emptyString()) }
    val (password, onPasswordChange) = remember { mutableStateOf(emptyString()) }
    val (showPassword, onShowPasswordClick) = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = uiState) {
        when (uiState.loginState) {
            LoginState.Success -> {
                navigateToHome()
            }

            LoginState.WrongCredential -> {
                scope.launch {
                    snackbarHostState.showSnackbar(context.getString(R.string.message_wrong_credentials))
                }
            }

            LoginState.Error -> {
                scope.launch {
                    snackbarHostState.showSnackbar(context.getString(R.string.message_something_wrong))
                }
            }

            else -> Unit
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeader(
                title = stringResource(id = R.string.login)
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
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
                viewModel.login(email, password)
            },
            onRegisterClick = navigateToRegister
        )
    }

    if (uiState.loadingState != LoadingState.NotLoading) {
        LoadingDialog()
    }
}