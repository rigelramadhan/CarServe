package one.reevdev.carserve.feature.common.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.feature.common.ui.component.AppHeader

@Composable
fun SuccessRouter(
    modifier: Modifier = Modifier,
    value: String,
    onProceed: () -> Unit,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeader(title = emptyString())
        }
    ) {
        SuccessScreen(
            modifier = Modifier.padding(it),
            value = value,
            onProceed = onProceed
        )
    }
}