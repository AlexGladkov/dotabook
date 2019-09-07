package com.agladkov.core.storage.dao

import androidx.room.Dao
import androidx.room.Query
import com.agladkov.core.storage.contracts.AntipickSqlContract
import com.agladkov.core.storage.contracts.RoomContract
import com.agladkov.core.storage.models.AntipickEntity

@Dao
interface AntipickDao {

    @Query("SELECT * FROM ${ RoomContract.tableAntipick} WHERE id = :id")
    fun fetchAntipicks(id: Int): List<AntipickEntity>

}