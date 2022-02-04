package com.agladkov.dotabook.data.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.agladkov.dotabook.data.storage.contracts.RoomContract

@Entity(tableName = RoomContract.tableHero)
data class HeroEntity(@PrimaryKey val id: Int, val displayName: String, val avatar: String,
                      val role: String)