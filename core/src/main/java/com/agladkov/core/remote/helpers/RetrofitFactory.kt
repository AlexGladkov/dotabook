package com.agladkov.core.remote.helpers

import com.agladkov.core.remote.services.HeroesService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class RetrofitFactory {

    companion object {
        val baseUrl = "https://api.opendota.com/api/"
        val baseImg = "https://api.opendota.com"

        private fun getOkHttpInstance(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC

            return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        }

        @ExperimentalSerializationApi
        private fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpInstance())
                .addConverterFactory(Json {
                    ignoreUnknownKeys = true
                }.asConverterFactory(contentType = "application/json".toMediaType()))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        @ExperimentalSerializationApi
        fun getHeroesService() = getRetrofitInstance().create(HeroesService::class.java)
    }
}