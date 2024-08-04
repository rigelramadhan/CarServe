package one.reevdev.carserve.feature.common.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

fun String.dialNumber(context: Context) {
    context.startActivity(
        Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:${this@dialNumber}")
        }
    )
}