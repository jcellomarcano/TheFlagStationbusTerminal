package com.example.thefalgbusstop.domain

import java.util.*


data class Chofer(
    val id: Int,
    val name: String,
    val lastname: String,
    val rut: String,
    val fullname: String = "${name.capitalize(Locale.ROOT)} ${lastname.capitalize(Locale.ROOT)}",
    val profileImg: String = "https://picsum.photos/60"

)

data class Route(
    val id: Int,
    val ida: String,
    val vueta: String,
    val terminal: String,
)

data class Bus(
    val id: Int,
    val patente: String,
    val marca: String,
    val choferId: Int,
)

data class Passenger(
    val id: Int,
    val name: String,
    val lastname: String,
    val rut: String,
)
data class Sit(
    val id: Int,
    val numAsiento: Int,
    val idBus: Int,
    val idPasenger: Int,
)
data class Horarios(
    val id: Int,
    val fecha: String,
    val hora: String,
    val idTrayecto: Int,
    val idBus: Int,
)
