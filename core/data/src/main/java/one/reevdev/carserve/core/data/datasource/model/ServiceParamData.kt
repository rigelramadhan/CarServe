package one.reevdev.carserve.core.data.datasource.model

import android.graphics.Bitmap

data class ServiceParamData(
    val symptoms: String,
    val generalProblem: String,
    val photo: Bitmap? = null
)
