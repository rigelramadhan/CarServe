package one.reevdev.carserve.feature.service.navigation.routes

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle

sealed class ServiceRoutes {

    @Serializable
    data class Service(val initVehicle: Vehicle)
}

val VehicleParameterType = object : NavType<Vehicle>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): Vehicle? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, Vehicle::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }
    }

    override fun put(bundle: Bundle, key: String, value: Vehicle) {
        bundle.putParcelable(key, value)
    }

    override fun parseValue(value: String): Vehicle {
        return Json.decodeFromString<Vehicle>(value)
    }

    override fun serializeAsValue(value: Vehicle): String {
        return Uri.encode(Json.encodeToString(value))
    }
}