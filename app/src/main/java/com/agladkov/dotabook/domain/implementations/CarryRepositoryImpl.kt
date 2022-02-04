package com.agladkov.dotabook.domain.implementations

import com.agladkov.dotabook.data.remote.helpers.RetrofitFactory
import com.agladkov.dotabook.data.remote.implementations.HeroesProvider
import com.agladkov.dotabook.data.storage.RoomAppDatabase
import com.agladkov.dotabook.domain.converters.HeroesConverter
import com.agladkov.dotabook.domain.models.Hero
import com.agladkov.dotabook.domain.models.HeroType
import java.lang.Exception
import javax.inject.Inject

class CarryRepository @Inject constructor(
    private val roomAppDatabase: RoomAppDatabase,
    private val heroesProvider: HeroesProvider,
    private val heroesConverter: HeroesConverter
) {

    suspend fun fetchLocalCarries(): List<Hero> {
        return roomAppDatabase.heroDao().fetchHeroes().map {
            heroesConverter.convertFromDbToDomain(heroEntity = it)
        }
    }

    suspend fun fetchCarries(): List<Hero> {
        return try {
            val heroes = heroesProvider.fetchHeroes()
            val stats = heroesProvider.fetchHeroStats()

            stats.map {
                it.icon = "${RetrofitFactory.baseImg}${it.icon}"
                it.img = "${RetrofitFactory.baseImg}${it.img}"
                it
            }

            heroes.map { heroApi ->
                val heroStat = stats.first { stat -> stat.id == heroApi.id }

                roomAppDatabase.heroDao().insertHero(
                    heroesConverter
                        .convertFromApiToDB(heroApi = heroApi, heroStat = heroStat)
                )

                heroesConverter.convertFromApiToDomain(
                    heroApi = heroApi,
                    heroStat = heroStat
                )
            }
                .filter { it.heroType == HeroType.Carry }
        } catch (e: Exception) {
             error(e)
        }
    }
}