package one.reevdev.carserve.feature.service.screen.camera

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.outlined.FlipCameraAndroid
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import one.reevdev.carserve.feature.service.R
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun CameraScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onGalleryPressed: () -> Unit,
    onCapturePressed: (() -> Unit)? = null,
    onSuccessCapture: (Uri?) -> Unit,
) {
    var lensFacing by remember { mutableIntStateOf(CameraSelector.LENS_FACING_BACK) }
    val preview = remember { Preview.Builder().build() }
    val previewView = remember { PreviewView(context) }
    val imageCapture = remember { ImageCapture.Builder().build() }

    LaunchedEffect(lensFacing) {
        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(lensFacing)
            .build()
        setupCamera(context, lifecycleOwner, cameraSelector, preview, previewView, imageCapture)
    }

    Box(modifier = modifier.fillMaxSize()) {
        AndroidView(modifier = Modifier.fillMaxSize(), factory = { previewView })
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val iconButtonSize = 48.dp
            FilledIconButton(
                modifier = Modifier
                    .size(iconButtonSize),
                onClick = onGalleryPressed
            ) {
                Icon(
                    imageVector = Icons.Outlined.PhotoLibrary,
                    contentDescription = stringResource(R.string.content_description_gallery_button)
                )
            }
            CaptureButton(
                modifier = Modifier,
                imageCapture = imageCapture,
                context = context,
                onButtonClick = onCapturePressed,
                onSuccessCapture = onSuccessCapture
            )
            FilledIconButton(
                modifier = Modifier
                    .size(iconButtonSize),
                onClick = {
                    lensFacing =
                        if (lensFacing == CameraSelector.LENS_FACING_BACK) CameraSelector.LENS_FACING_FRONT
                        else CameraSelector.LENS_FACING_BACK
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.FlipCameraAndroid,
                    contentDescription = stringResource(R.string.content_description_flip_camera_button)
                )
            }
        }
    }
}

@Composable
fun CaptureButton(
    modifier: Modifier = Modifier,
    imageCapture: ImageCapture,
    context: Context,
    onButtonClick: (() -> Unit)? = null,
    onSuccessCapture: (Uri?) -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = {
            onButtonClick?.invoke()
            captureImage(imageCapture, context, onSuccessCapture)
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Camera,
            contentDescription = stringResource(R.string.content_description_capture_image)
        )
    }
}

private suspend fun setupCamera(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    cameraSelector: CameraSelector,
    preview: Preview,
    previewView: PreviewView,
    imageCapture: ImageCapture,
) {
    val cameraProvider = context.getCameraProvider()
    cameraProvider.unbindAll()
    cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageCapture)
    preview.setSurfaceProvider(previewView.surfaceProvider)
}

private fun captureImage(
    imageCapture: ImageCapture,
    context: Context,
    onSuccessCapture: (uri: Uri?) -> Unit
) {
    val name = "car-serve-${System.currentTimeMillis()}.jpeg"
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/CarServe")
        }
    }
    val outputOptions = ImageCapture.OutputFileOptions
        .Builder(
            context.contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )
        .build()
    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                onSuccessCapture(outputFileResults.savedUri)
            }

            override fun onError(exception: ImageCaptureException) {
                // TODO: Log error
                Log.e("TEST", exception.localizedMessage.orEmpty())
            }
        }
    )
}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider =
    suspendCoroutine { continuation ->
        ProcessCameraProvider.getInstance(this).also { cameraProvider ->
            cameraProvider.addListener({
                continuation.resume(cameraProvider.get())
            }, ContextCompat.getMainExecutor(this))
        }
    }