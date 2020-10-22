package com.example.androidthingsapp.viewmodel

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.ObservableBoolean
import com.example.androidthingsapp.multipleselectable.SelectableItem

class TeamViewModel(override val id: Int, val name: String, val type: Type = Type.FOOTBALL) :
    SelectableItem, Parcelable {

    enum class Type {
        FOOTBALL,
        FAVORITE
    }

    override var isSelected = ObservableBoolean(false)

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        TODO("type")
    ) {
        isSelected = parcel.readParcelable(ObservableBoolean::class.java.classLoader)!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeParcelable(isSelected, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TeamViewModel> {
        override fun createFromParcel(parcel: Parcel): TeamViewModel {
            return TeamViewModel(parcel)
        }

        override fun newArray(size: Int): Array<TeamViewModel?> {
            return arrayOfNulls(size)
        }
    }
}