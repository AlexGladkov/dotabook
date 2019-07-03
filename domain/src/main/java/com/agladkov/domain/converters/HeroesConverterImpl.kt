package com.agladkov.domain.converters

import com.agladkov.core.remote.model.HeroApi
import com.agladkov.core.remote.model.HeroStat
import com.agladkov.domain.models.Hero
import com.agladkov.domain.models.HeroType

class HeroesConverterImpl: HeroesConverter {

    override fun convertFromApiToDomain(heroApi: HeroApi, heroStat: HeroStat): Hero {
        return Hero(id = heroApi.id, name = heroApi.name, avatar = heroStat.img, heroType = when {
            heroApi.roles.contains("Carry") -> HeroType.Carry
            heroApi.roles.contains("Support") -> HeroType.Support
            heroApi.roles.contains("Durable") -> HeroType.Hardlane
            else -> HeroType.Unknown
        })
    }
}