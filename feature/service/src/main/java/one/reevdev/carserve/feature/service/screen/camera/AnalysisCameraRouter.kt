package one.reevdev.carserve.feature.service.screen.camera

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import one.reevdev.carserve.feature.service.component.CameraScreen
import one.reevdev.carserve.feature.service.screen.AnalysisSharedViewModel
import one.reevdev.carserve.feature.service.utils.toBitmap

@Composable
fun AnalysisCameraRouter(
    modifier: Modifier = Modifier,
    viewModel: AnalysisSharedViewModel = hiltViewModel(),
    context: Context,
    proceedToForm: (Uri?) -> Unit,
) {
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        CameraScreen(
            modifier = Modifier
                .padding(innerPadding),
            onSuccessCapture = {
                viewModel.setPhoto(it?.toBitmap(context))
                proceedToForm(it)
            }
        )
    }
}