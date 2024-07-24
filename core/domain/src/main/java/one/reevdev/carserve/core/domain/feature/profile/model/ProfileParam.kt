package one.reevdev.carserve.core.domain.feature.profile.model

import one.reevdev.carserve.core.common.data.emptyString

data class ProfileParam(
    val name: String = emptyString(),
    val email: String = emptyString(),
    val phoneNumber: String = emptyString(),
    val address: String = emptyString()
)