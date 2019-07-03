package com.agladkov.domain.implementations

import com.agladkov.core.remote.helpers.RetrofitFactory
import com.agladkov.core.remote.implementations.HeroesProviderImpl
import com.agladkov.domain.CarryRepository
import com.agladkov.domain.converters.HeroesConverterImpl
import com.agladkov.domain.models.Hero
import com.agladkov.domain.models.HeroType
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.lang.Exception

class CarryRepositoryImpl : CarryRepository {

    override suspend fun fetchCarries(): Deferred<List<Hero>> {
        val heroesProvider = HeroesProviderImpl()
        val heroesConverter = HeroesConverterImpl()

        try {
            val heroes = heroesProvider.fetchHeroes().await()
            val stats = heroesProvider.fetchHeroStats().await()

            return GlobalScope.async {
                stats.map {
                    it.icon = "${RetrofitFactory.baseImg}${it.icon}"
                    it.img = "${RetrofitFactory.baseImg}${it.img}"
                    it
                }

                heroes.map {
                        heroesConverter.convertFromApiToDomain(
                            heroApi = it,
                            heroStat = stats.first { stat -> stat.id == it.id })
                    }
                    .filter { it.heroType == HeroType.Carry }
            }
        } catch (e: Exception) {
            return GlobalScope.async { error(e) }
        }
    }
}