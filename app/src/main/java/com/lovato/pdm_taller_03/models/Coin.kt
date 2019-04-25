package com.lovato.pdm_taller_03.models

import android.os.Parcel
import android.os.Parcelable

data class Coin(val name: String = "N/A", 
                val country: String = "N/A", 
                val value_us: Double = 0.0,
                val year: Int = 0,
                val isAvailable: Int = 0,
                val img: String = "N/A"
) : Parcelable {
    constructor(parcel: Parcel) : this(
        name = parcel.readString(),
        country = parcel.readString(),
        value_us = parcel.readDouble(),
        year = parcel.readInt(),
        isAvailable = parcel.readInt(),
        img = parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(country)
        parcel.writeDouble(value_us)
        parcel.writeInt(year)
        parcel.writeInt(isAvailable)
        parcel.writeString(img)

    }

    override fun describeContents() = 0

    companion object {
            @JvmField
            val CREATOR = object : Parcelable.Creator<Coin> {
                override fun createFromParcel(parcel: Parcel): Coin = Coin(parcel)
                override fun newArray(size: Int): Array<Coin?> = arrayOfNulls(size)
            }
    }

}