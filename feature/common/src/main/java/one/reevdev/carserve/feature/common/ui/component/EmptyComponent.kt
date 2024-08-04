package one.reevdev.carserve.feature.common.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.feature.common.R
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme

@Composable
fun EmptyComponent(
    modifier: Modifier = Modifier,
    text: String? = null,
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(142.dp),
            painter = painterResource(id = R.drawable.empty_illustration),
            contentDescription = null
        )
        text?.let {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                text = text
            )
        }
    }
}

@Preview
@Composable
private fun EmptyComponentPreview() {
    CarServeTheme {
        EmptyComponent(text = "No content found.")
    }
}