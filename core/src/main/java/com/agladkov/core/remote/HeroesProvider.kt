package com.agladkov.core.remote

import com.agladkov.core.remote.helpers.ApiResponse
import com.agladkov.core.remote.model.HeroApi
import com.agladkov.core.remote.model.HeroStat
import kotlinx.coroutines.Deferred

interface HeroesProvider {
    suspend fun fetchHeroes(): Deferred<List<HeroApi>>
    suspend fun fetchHeroStats(): Deferred<List<HeroStat>>
}