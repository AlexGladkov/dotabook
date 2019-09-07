package com.agladkov.core.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.agladkov.core.storage.contracts.RoomContract

@Entity(tableName = RoomContract.tableAntipick)
data class AntipickEntity(@PrimaryKey val id: Int, val antipickId: Int, val displayName: String,
                          val avatar: String, val role: String)