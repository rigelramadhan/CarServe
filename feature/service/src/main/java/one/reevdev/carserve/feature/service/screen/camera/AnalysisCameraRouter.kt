package one.reevdev.carserve.feature.service.screen.camera

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.feature.common.ui.component.AppHeader
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import one.reevdev.carserve.feature.service.screen.ServiceAnalysisViewModel
import one.reevdev.carserve.feature.service.utils.toBitmap

@Composable
fun AnalysisCameraRouter(
    modifier: Modifier = Modifier,
    viewModel: ServiceAnalysisViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    proceedToForm: (Uri?) -> Unit,
) {
    var mediaUri by remember { mutableStateOf<Uri?>(null) }

    val pickMedia = rememberLauncherForActivityResult(contract = PickVisualMedia()) { uri ->
        if (uri != null) {
            viewModel.setLoading(LoadingState.DefaultLoading)
            mediaUri = uri
        }
    }

    LaunchedEffect(key1 = mediaUri) {
        if (mediaUri != null) {
            viewModel.setPhoto(mediaUri?.toBitmap(context))
            proceedToForm(mediaUri)
            mediaUri = null
        }
    }

    Box(
        modifier = modifier,
    ) {
        CameraScreen(
            modifier = Modifier,
            onCapturePressed = {
                viewModel.setLoading(LoadingState.DefaultLoading)
            },
            onGalleryPressed = {
                pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
            },
            onSuccessCapture = {
                mediaUri = it
            }
        )
        AppHeader(title = emptyString(), isTransparent = true, hasBackButton = true)
    }
}