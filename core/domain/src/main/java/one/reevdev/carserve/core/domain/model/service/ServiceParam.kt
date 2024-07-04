package one.reevdev.carserve.core.domain.model.service

import android.graphics.Bitmap

data class ServiceParam(
    val symptoms: String,
    val generalProblem: String,
    val photo: Bitmap? = null
)
