package com.app.travelmate.domain.exception

import androidx.annotation.StringRes

data class APIException(
    @StringRes
    val genericError: Int? = null,
    val apiError: String? = null
)
