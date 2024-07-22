package one.reevdev.carserve.feature.profile.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import one.reevdev.carserve.core.common.data.emptyString

@Composable
fun LoginRouter(modifier: Modifier = Modifier) {
    val (email, onEmailChange) = remember { mutableStateOf(emptyString()) }
    val (password, onPasswordChange) = remember { mutableStateOf(emptyString()) }


}