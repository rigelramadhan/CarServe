package one.reevdev.carserve.core.domain.feature.service.model

import android.graphics.Bitmap
import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.core.domain.feature.profile.model.SavedProfile
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle

data class ServiceParam(
    val symptoms: String = emptyString(),
    val generalProblem: String = emptyString(),
    val vehicle: Vehicle = Vehicle(),
    val profile: SavedProfile = SavedProfile(),
    val photo: Bitmap? = null
)
