package com.agladkov.domain.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


enum class HeroType {
    Carry, Support, Hardlane, Unknown
}

data class Hero(val id: Int, val name: String, val avatar: String, val heroType: HeroType): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        when (parcel.readInt()) {
            0 -> HeroType.Carry
            1 -> HeroType.Support
            2 -> HeroType.Hardlane
            else -> HeroType.Unknown
        }
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(avatar)
        parcel.writeInt(when (heroType) {
            HeroType.Carry -> 0
            HeroType.Support -> 1
            HeroType.Hardlane -> 2
            HeroType.Unknown -> 3
        })
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hero> {
        override fun createFromParcel(parcel: Parcel): Hero {
            return Hero(parcel)
        }

        override fun newArray(size: Int): Array<Hero?> {
            return arrayOfNulls(size)
        }
    }
}