package com.agladkov.dotabook.data.storage.contracts

class AntipickSqlContract {

    companion object {
        const val fetch = "SELECT * FROM ${RoomContract.tableAntipick} WHERE id = [HERO_ID]"
    }
}