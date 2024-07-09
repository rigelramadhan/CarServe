package one.reevdev.carserve.core.domain.model.service

import android.graphics.Bitmap
import one.reevdev.carserve.core.common.data.emptyString

data class ServiceParam(
    val symptoms: String = emptyString(),
    val generalProblem: String = emptyString(),
    val photo: Bitmap? = null
)
