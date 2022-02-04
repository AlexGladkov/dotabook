package com.agladkov.dotabook.data.storage.contracts

class HeroSqlContract {

    companion object {
        const val fetch = "SELECT * FROM ${RoomContract.tableHero}"
    }
}