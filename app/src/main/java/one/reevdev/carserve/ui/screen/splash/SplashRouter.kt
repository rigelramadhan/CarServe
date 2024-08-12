package one.reevdev.carserve.ui.screen.splash

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashRouter(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        viewModel.checkLoggedInUser()
    }

    LaunchedEffect(key1 = uiState) {
        when (uiState.postDestination) {
            PostSplashDestination.AUTH -> {
                scope.launch {
                    delay(500)
                    navigateToLogin()
                }
            }
            PostSplashDestination.HOME -> {
                scope.launch {
                    delay(500)
                    navigateToHome()
                }
            }
            else -> Unit
        }
    }

    Scaffold(modifier) {
        SplashScreen(Modifier.padding(it))
    }
}