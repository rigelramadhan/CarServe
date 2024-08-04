package one.reevdev.carserve.core.domain.feature.profile.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import one.reevdev.carserve.core.common.data.emptyString

@Serializable
@Parcelize
data class Customer(
    val name: String = emptyString(),
    val email: String = emptyString(),
    val phoneNumber: String = emptyString(),
    val address: String = emptyString()
) : Parcelable
