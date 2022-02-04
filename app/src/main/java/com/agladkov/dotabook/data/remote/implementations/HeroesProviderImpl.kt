package com.agladkov.dotabook.data.remote.implementations

import com.agladkov.dotabook.data.remote.helpers.RetrofitFactory
import com.agladkov.dotabook.data.remote.model.HeroApi
import com.agladkov.dotabook.data.remote.model.HeroStat
import com.agladkov.dotabook.data.remote.services.HeroesService
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class HeroesProvider @Inject constructor(
    private val heroesService: HeroesService
) {

    suspend fun fetchHeroes(): List<HeroApi> {
        return heroesService.getHeroes()
    }

    suspend fun fetchHeroStats(): List<HeroStat> {
        return heroesService.getHeroesStats()
    }
}