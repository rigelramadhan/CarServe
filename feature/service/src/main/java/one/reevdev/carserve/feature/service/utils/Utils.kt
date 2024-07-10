package one.reevdev.carserve.feature.service.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import androidx.exifinterface.media.ExifInterface

fun Uri.toBitmap(context: Context): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(this)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()

        bitmap?.let {
            val exif = context.contentResolver.openInputStream(this)?.use { stream ->
                ExifInterface(stream)
            }
            val rotation = exif?.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
            val rotatedBitmap = when (rotation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(it, 90f)
                ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(it, 180f)
                ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(it, 270f)
                else -> it
            }
            rotatedBitmap
        }
    } catch (e: Exception) {
        // TODO: Handle error
        null
    }
}

fun rotateBitmap(bitmap: Bitmap, degrees: Float): Bitmap {
    val matrix = Matrix().apply { postRotate(degrees) }
    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
}