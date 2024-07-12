package one.reevdev.carserve.feature.common.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme

@Composable
fun CarseTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    enabledIf: () -> Boolean = { true },
    onValueChange: (String) -> Unit,
) {
    TextField(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)),
        value = value,
        enabled = enabledIf(),
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun CarseTextFieldPreview() {
    CarServeTheme {
        CarseTextField(value = "This is the value", label = "Field") {
            
        }
    }
}