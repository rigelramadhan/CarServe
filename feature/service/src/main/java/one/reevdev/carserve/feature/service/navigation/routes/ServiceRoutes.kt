package one.reevdev.carserve.feature.service.navigation.routes

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle

sealed class ServiceRoutes {

    @Serializable
    data class Service(val initVehicle: CustomerVehicle)
}

val CustomerVehicleParameterType = object : NavType<CustomerVehicle>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): CustomerVehicle? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, CustomerVehicle::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }
    }

    override fun put(bundle: Bundle, key: String, value: CustomerVehicle) {
        bundle.putParcelable(key, value)
    }

    override fun parseValue(value: String): CustomerVehicle {
        return Json.decodeFromString<CustomerVehicle>(value)
    }

    override fun serializeAsValue(value: CustomerVehicle): String {
        return Uri.encode(Json.encodeToString(value))
    }
}