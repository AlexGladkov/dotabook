package com.agladkov.core.remote.services

import com.agladkov.core.remote.model.HeroApi
import com.agladkov.core.remote.model.HeroStat
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.GET

interface HeroesService {

    @GET("./heroes")
    fun getHeroes(): Deferred<List<HeroApi>>

    @GET("./heroStats")
    fun getHeroesStats(): Deferred<List<HeroStat>>
}