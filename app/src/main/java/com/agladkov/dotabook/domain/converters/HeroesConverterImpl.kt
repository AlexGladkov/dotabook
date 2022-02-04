package com.agladkov.dotabook.domain.converters

import com.agladkov.dotabook.data.remote.model.HeroApi
import com.agladkov.dotabook.data.remote.model.HeroStat
import com.agladkov.dotabook.data.storage.models.HeroEntity
import com.agladkov.dotabook.domain.models.Hero
import com.agladkov.dotabook.domain.models.HeroType
import javax.inject.Inject

class HeroesConverter @Inject constructor() {

    fun convertFromApiToDomain(heroApi: HeroApi, heroStat: HeroStat): Hero {
        return Hero(id = heroApi.id, name = heroApi.name, avatar = heroStat.img, heroType = when {
            heroApi.roles.contains("Carry") -> HeroType.Carry
            heroApi.roles.contains("Support") -> HeroType.Support
            heroApi.roles.contains("Durable") -> HeroType.Hardlane
            else -> HeroType.Unknown
        })
    }

    fun convertFromDbToDomain(heroEntity: HeroEntity): Hero {
        return Hero(id = heroEntity.id, name = heroEntity.displayName, avatar = heroEntity.avatar,
            heroType = when {
            heroEntity.role == HeroType.Carry.name -> HeroType.Carry
            else -> HeroType.Unknown
        })
    }

    fun convertFromApiToDB(heroApi: HeroApi, heroStat: HeroStat): HeroEntity {
        val role = if (heroApi.roles.isNotEmpty()) {
            heroApi.roles.first()
        } else {
            HeroType.Unknown.name
        }

        return HeroEntity(id = heroApi.id, displayName = heroApi.localized_name,
            avatar = heroStat.img, role = role)
    }
}