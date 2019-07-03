package com.agladkov.core.remote.helpers

data class ApiResponse<out T>(val success: T? = null, val error: Throwable? = null)