package one.reevdev.carserve.domain.model.service

import java.io.File

data class ServiceParam(
    val symptoms: String,
    val generalProblem: String,
    val photo: File? = null
)
