package one.reevdev.carserve.feature.common.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun LabelText(
    modifier: Modifier = Modifier,
    label: String,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
) {
    Text(
        modifier = modifier,
        text = label,
        style = style,
        color = color
    )
}