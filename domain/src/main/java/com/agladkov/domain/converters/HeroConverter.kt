package com.agladkov.domain.converters

import com.agladkov.core.remote.model.HeroApi
import com.agladkov.core.remote.model.HeroStat
import com.agladkov.core.storage.models.HeroEntity
import com.agladkov.domain.models.Hero

interface HeroesConverter {
    fun convertFromApiToDomain(heroApi: HeroApi, heroStat: HeroStat): Hero
    fun convertFromApiToDB(heroApi: HeroApi, heroStat: HeroStat): HeroEntity
    fun convertFromDbToDomain(heroEntity: HeroEntity): Hero
}