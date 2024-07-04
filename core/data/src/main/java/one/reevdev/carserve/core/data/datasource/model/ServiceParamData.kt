package one.reevdev.carserve.core.data.datasource.model

import java.io.File

data class ServiceParamData(
    val symptoms: String,
    val generalProblem: String,
    val photo: File? = null
)
