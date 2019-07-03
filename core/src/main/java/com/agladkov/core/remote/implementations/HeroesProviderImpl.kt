package com.agladkov.core.remote.implementations

import com.agladkov.core.remote.HeroesProvider
import com.agladkov.core.remote.helpers.ApiResponse
import com.agladkov.core.remote.helpers.CompleteFactory
import com.agladkov.core.remote.helpers.RetrofitFactory
import com.agladkov.core.remote.model.HeroApi
import com.agladkov.core.remote.model.HeroStat
import kotlinx.coroutines.Deferred

class HeroesProviderImpl : HeroesProvider {
    private val TAG = HeroesProviderImpl::class.java.simpleName

    override suspend fun fetchHeroes(): Deferred<List<HeroApi>> {
        return RetrofitFactory.getHeroesService().getHeroes()
    }

    override suspend fun fetchHeroStats(): Deferred<List<HeroStat>> {
        return RetrofitFactory.getHeroesService().getHeroesStats()
    }
}