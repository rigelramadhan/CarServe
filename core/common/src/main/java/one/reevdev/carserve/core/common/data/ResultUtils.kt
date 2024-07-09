package one.reevdev.carserve.core.common.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <Original, Mapped> Result<Original>.mapSuccessData(
    mapping: (Original) -> Mapped
): Result<Mapped> {
    return when (this) {
        is Result.Success -> {
            Result.Success(mapping(this.data))
        }

        is Result.Error -> {
            Result.Error(this.error, this.errorMessage)
        }

        is Result.Loading -> {
            Result.Loading()
        }
    }
}

fun <Original, Mapped> Flow<Result<Original>>.mapFlowData(
    mapping: (Original) -> Mapped
): Flow<Result<Mapped>> {
    return this.map { resource ->
        resource.mapSuccessData {
            mapping(it)
        }
    }
}

fun <T, UiState> Result<T>.handleResource(
    onSuccess: (T) -> UiState,
    onFailure: (Throwable, String?) -> UiState,
    onLoading: (T?) -> UiState
): UiState {
    return when (this) {
        is Result.Success -> {
            onSuccess(this.data)
        }

        is Result.Error -> {
            onFailure(this.error, this.errorMessage)
        }

        is Result.Loading -> {
            onLoading(this.data)
        }
    }
}