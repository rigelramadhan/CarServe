package one.reevdev.carserve.feature.common.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme

@Composable
fun TextWithLabel(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
) {
    Column(modifier = modifier) {
        LabelText(
            label = label,
            color =  MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
        )
        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = text
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextWithLabelPreview() {
    CarServeTheme {
        TextWithLabel(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            label = "Name",
            text = "John Doe"
        )
    }
}