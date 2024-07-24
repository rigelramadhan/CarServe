package one.reevdev.carserve.feature.service.navigation.routes

import kotlinx.serialization.Serializable

sealed class AnalysisRoutes {
    @Serializable
    data object Camera

    @Serializable
    data object Form

    @Serializable
    data object Analysis

    @Serializable
    data class PdfViewer(val pdfPath: String)
}