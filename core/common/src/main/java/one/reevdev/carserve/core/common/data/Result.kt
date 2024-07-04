package one.reevdev.carserve.core.common.data

sealed class Result<out T> {
    data class Loading<out T>(val data: T? = null) : Result<T>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(val error: Exception, val errorMessage: String? = null) : Result<T>()
}