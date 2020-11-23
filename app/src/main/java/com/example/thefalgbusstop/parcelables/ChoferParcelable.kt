package com.example.thefalgbusstop.parcelables

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChoferParcelable(
    val id: Int,
    val name: String,
    val lastname: String,
    val rut: String,
    val fullname: String = "${name.capitalize()} ${lastname.capitalize()}",
    val profileImg: String = "https://picsum.photos/60"

): Parcelable