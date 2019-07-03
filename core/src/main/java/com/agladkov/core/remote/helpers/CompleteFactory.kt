package com.agladkov.core.remote.helpers

class CompleteFactory {

    companion object {
        fun appendBaseUrl(url: String): String = "${RetrofitFactory.baseImg}$url"
    }
}