package one.reevdev.carserve.feature.common.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import one.reevdev.carserve.feature.common.R

@Composable
fun LoadingDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation))

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(composition = composition)
        }
    }
}