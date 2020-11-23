package com.example.thefalgbusstop.domain.entities


data class Chofer(
    val id: Int,
    val name: String,
    val lastname: String,
    val rut: String,
    val fullname: String = "${name.capitalize()} ${lastname.capitalize()}",
    val profileImg: String = "https://picsum.photos/60"

)

data class ChoferPost(
    val nombre: String,
    val apellido: String,
    val rut: String,
)