package one.reevdev.carserve.feature.service.screen.camera

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import one.reevdev.carserve.feature.service.component.CameraScreen
import one.reevdev.carserve.feature.service.screen.ServiceAnalysisViewModel
import one.reevdev.carserve.feature.service.utils.toBitmap

@Composable
fun AnalysisCameraRouter(
    modifier: Modifier = Modifier,
    viewModel: ServiceAnalysisViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    proceedToForm: (Uri?) -> Unit,
) {
    CameraScreen(
        modifier = modifier,
        onCapturePressed = {
            viewModel.setLoading(LoadingState.DefaultLoading)
        },
        onSuccessCapture = {
            viewModel.setPhoto(it?.toBitmap(context))
            proceedToForm(it)
        }
    )
}