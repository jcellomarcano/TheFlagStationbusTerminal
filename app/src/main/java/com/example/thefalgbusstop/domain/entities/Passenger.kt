package com.example.thefalgbusstop.domain.entities

data class Passenger(
    val id: Int,
    val name: String,
    val lastname: String,
    val rut: String,
    val fullname: String = "${name.capitalize()} ${lastname.capitalize()}",
    val profileImg: String = "https://picsum.photos/60"

)