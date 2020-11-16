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

@Parcelize
data class RouteParcelable(
    val id: Int,
    val ida: String,
    val vuelta: String,
    val terminal: String,

    ):Parcelable
@Parcelize
data class BusParcelable(
    val id: Int,
    val patente: String,
    val marca: String,
    val choferId: Int,
    val busImg: String = "https://cms-assets.tutsplus.com/uploads/users/158/posts/24850/preview_image/busPreview.jpg"
):Parcelable {
    override fun toString(): String {
        return "Bus(choferId=$choferId)"
    }
}
@Parcelize
data class PassengerParcelable(
    val id: Int,
    val name: String,
    val lastname: String,
    val rut: String,
    val fullname: String = "${name.capitalize()} ${lastname.capitalize()}",
    val profileImg: String = "https://picsum.photos/60"

):Parcelable
@Parcelize
data class SitParcelable(
    val id: Int,
    val numAsiento: Int,
    val idBus: Int,
    val idPasenger: Int,
):Parcelable
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
