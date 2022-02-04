package com.agladkov.dotabook.data.remote.services

import com.agladkov.dotabook.data.remote.model.HeroApi
import com.agladkov.dotabook.data.remote.model.HeroStat
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface HeroesService {

    @GET("./heroes")
    suspend fun getHeroes(): List<HeroApi>

    @GET("./heroStats")
    suspend fun getHeroesStats(): List<HeroStat>
}