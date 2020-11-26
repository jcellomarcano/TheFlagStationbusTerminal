package com.example.thefalgbusstop.parcelables

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BusParcelable(
    val id: Int,
    val patente: String,
    val marca: String,
    val choferId: Int,
    val busImg: String = "https://cms-assets.tutsplus.com/uploads/users/158/posts/24850/preview_image/busPreview.jpg"
): Parcelable {
    override fun toString(): String {
        return "Bus(choferId=$choferId)"
    }
}