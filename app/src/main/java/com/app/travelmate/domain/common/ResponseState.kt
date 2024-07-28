package com.app.travelmate.domain.common

import androidx.annotation.StringRes

sealed class ResponseState<out T> {
    data class SuccessState<T>(val data: T) : ResponseState<T>()
    data class ErrorState<T>(@StringRes val message: Int, val data: T? = null) : ResponseState<T>()
    object Loading : ResponseState<Nothing>()
}