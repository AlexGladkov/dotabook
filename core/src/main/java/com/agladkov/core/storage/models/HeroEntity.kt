package com.agladkov.core.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.agladkov.core.storage.contracts.RoomContract

@Entity(tableName = RoomContract.tableHero)
data class HeroEntity(@PrimaryKey val id: Int, val displayName: String, val avatar: String,
                      val role: String)