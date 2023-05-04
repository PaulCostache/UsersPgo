package com.example.userspgo.Model

import android.os.Parcel
import android.os.Parcelable

data class UserModelItem(
    val email: String,
    val gender: String,
    val id: Int,
    val name: String,
    val status: String,
    var imageUrl: String? =null,
    val initials: String?,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(gender)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(status)
        parcel.writeString(imageUrl)
        parcel.writeString(initials)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModelItem> {
        override fun createFromParcel(parcel: Parcel): UserModelItem {
            return UserModelItem(parcel)
        }

        override fun newArray(size: Int): Array<UserModelItem?> {
            return arrayOfNulls(size)
        }
    }
}