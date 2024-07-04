package one.reevdev.carserve.core.domain.model

sealed class Result<T>(data: T? = null, errorMessage: String? = null) {
    data class Loading<T>(val data: T? = null) : Result<T>(data)
    data class Success<T>(val data: T? = null) : Result<T>(data)
    data class Error<T>(val error: Exception, val errorMessage: String? = null) :
        Result<T>(errorMessage = errorMessage ?: error.localizedMessage)
}