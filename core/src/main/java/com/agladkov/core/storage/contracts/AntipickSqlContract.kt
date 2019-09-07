package com.agladkov.core.storage.contracts

class AntipickSqlContract {

    companion object {
        const val fetch = "SELECT * FROM ${RoomContract.tableAntipick} WHERE id = [HERO_ID]"
    }
}