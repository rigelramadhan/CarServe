package one.reevdev.carserve.core.data.feature.vehicle.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class VehicleResponse(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("brand")
    val carBrand: String,

    @SerializedName("name")
    val carName: String,

    @SerializedName("model")
    val carType: String,

    @SerializedName("color")
    val color: String,

    @SerializedName("transmission")
    val transmission: String,
)

