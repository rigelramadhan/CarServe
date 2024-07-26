package one.reevdev.carserve.feature.service.navigation.routes

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis

sealed class AnalysisRoutes {
    @Serializable
    data object Camera

    @Serializable
    data object Form

    @Serializable
    data object Analysis

    @Serializable
    data class PdfViewer(val pdfPath: String)

    @Serializable
    data object AnalysisHistory

    @Serializable
    data class AnalysisDetail(val analysis: ServiceAnalysis)
}

val AnalysisParameterType = object : NavType<ServiceAnalysis>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): ServiceAnalysis? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, ServiceAnalysis::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }
    }

    override fun put(bundle: Bundle, key: String, value: ServiceAnalysis) {
        bundle.putParcelable(key, value)
    }

    override fun parseValue(value: String): ServiceAnalysis {
        return Json.decodeFromString<ServiceAnalysis>(value)
    }

    override fun serializeAsValue(value: ServiceAnalysis): String {
        return Uri.encode(Json.encodeToString(value))
    }
}