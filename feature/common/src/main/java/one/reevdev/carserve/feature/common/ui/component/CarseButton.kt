package one.reevdev.carserve.feature.common.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CarseButton(
    modifier: Modifier = Modifier,
    text: String,
    enableIf: () -> Boolean = { true },
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enableIf()
    ) {
        Text(text = text)
    }
}