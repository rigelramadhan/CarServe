package one.reevdev.carserve.core.domain.feature.vehicle.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import one.reevdev.carserve.core.common.data.emptyString

@Parcelize
@Serializable
data class CustomerVehicle(
    val policeNo: String = emptyString(),
    val ownerEmail: String = emptyString(),
    val carBrand: String = emptyString(),
    val carName: String = emptyString(),
    val carType: String = emptyString(),
    val color: String = emptyString(),
    val transmission: String = emptyString(),
) : Parcelable
