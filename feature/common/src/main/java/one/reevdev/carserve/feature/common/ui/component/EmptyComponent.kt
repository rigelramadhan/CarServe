package one.reevdev.carserve.feature.common.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme

@Composable
fun EmptyComponent(
    modifier: Modifier = Modifier,
    text: String? = null,
) {
//    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation))

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        LottieAnimation(composition = composition)
        text?.let {
            Text(
                modifier = Modifier
                    .padding(top = 8.dp),
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