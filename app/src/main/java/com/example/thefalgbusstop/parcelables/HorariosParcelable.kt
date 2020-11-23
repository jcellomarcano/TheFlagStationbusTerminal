package com.example.thefalgbusstop.parcelables

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HorariosParcelable(
    val id: Int,
    val fecha: String,
    val hora: String,
    val idTrayecto: Int,
    val idBus: Int,
):Parcelable {
    override fun toString(): String {
        return "Salida: $fecha $hora"
    }

}
