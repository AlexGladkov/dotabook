package com.agladkov.dotabook.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agladkov.dotabook.data.storage.contracts.HeroSqlContract
import com.agladkov.dotabook.data.storage.models.HeroEntity

@Dao
interface HeroDao {

    @Query(HeroSqlContract.fetch)
    suspend fun fetchHeroes(): List<HeroEntity>

    @Insert(entity = HeroEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHero(heroEntity: HeroEntity)
}