package com.example.thefalgbusstop.parcelables

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RouteParcelable(
    val id: Int,
    val ida: String,
    val vuelta: String,
    val terminal: String,

    ): Parcelable