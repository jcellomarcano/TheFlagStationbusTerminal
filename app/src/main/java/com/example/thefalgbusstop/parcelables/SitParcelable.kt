package com.example.thefalgbusstop.parcelables

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SitParcelable(
    val id: Int,
    val numAsiento: Int,
    val idBus: Int,
    val idPasenger: Int,
): Parcelable