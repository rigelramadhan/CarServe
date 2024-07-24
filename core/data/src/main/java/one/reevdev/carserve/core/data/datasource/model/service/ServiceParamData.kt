package one.reevdev.carserve.core.data.datasource.model.service

import android.graphics.Bitmap
import one.reevdev.carserve.core.data.datasource.model.profile.LastSavedProfile
import one.reevdev.carserve.core.data.datasource.model.vehicle.VehicleEntity

data class ServiceParamData(
    val symptoms: String,
    val generalProblem: String,
    val vehicle: VehicleEntity,
    val profile: LastSavedProfile,
    val photo: Bitmap? = null
)
