package com.agladkov.dotabook.data.remote.helpers

data class ApiResponse<out T>(val success: T? = null, val error: Throwable? = null)