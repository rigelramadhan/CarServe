package one.reevdev.carserve.feature.service.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri

fun Uri.toBitmap(context: Context): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(this)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()
        bitmap
    } catch (e: Exception) {
        // TODO: Handle error
        null
    }
}