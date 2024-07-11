package one.reevdev.carserve.core.data.datasource.model

import com.google.gson.annotations.SerializedName

data class AvailableService(
    @SerializedName("service")
    val service: String,
    @SerializedName("estimated_price")
    val price: Double
)
