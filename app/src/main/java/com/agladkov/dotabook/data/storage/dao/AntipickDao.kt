package com.agladkov.dotabook.data.storage.dao

import androidx.room.Dao
import androidx.room.Query
import com.agladkov.dotabook.data.storage.contracts.RoomContract
import com.agladkov.dotabook.data.storage.models.AntipickEntity

@Dao
interface AntipickDao {

    @Query("SELECT * FROM ${ RoomContract.tableAntipick} WHERE id = :id")
    fun fetchAntipicks(id: Int): List<AntipickEntity>

}