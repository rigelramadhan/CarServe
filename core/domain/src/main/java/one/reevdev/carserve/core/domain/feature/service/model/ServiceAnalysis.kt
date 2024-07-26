package one.reevdev.carserve.core.domain.feature.service.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.core.domain.feature.profile.model.SavedProfile
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle

@Serializable
@Parcelize
data class ServiceAnalysis(
    val vehicle: Vehicle? = null,
    val profile: SavedProfile? = null,
    val recommendedAction: String = emptyString(),
    val serviceFindings: List<ServiceFinding> = emptyList(),
    val totalEstimatedPrice: Double = 0.0,
    val analysisHtml: String = emptyString(),
) : Parcelable

@Serializable
@Parcelize
data class ServiceFinding(
    val problem: String,
    val solution: String,
    val estimatedPrice: Double,
) : Parcelable
