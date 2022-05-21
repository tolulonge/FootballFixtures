package com.tolulonge.footballfixtures.core.util


enum class ApiStatus {
    SUCCESS,
    ERROR,
    LOADING
}

sealed class Resource<T>(val status: ApiStatus, val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(
        status = ApiStatus.SUCCESS,
        data = data
    )
    class Error<T>(message: String, data: T? = null): Resource<T>(
        status = ApiStatus.ERROR,
        message = message
    )
    class Loading<T>(val isLoading: Boolean = true): Resource<T>(
        status = ApiStatus.LOADING,
    )
}
