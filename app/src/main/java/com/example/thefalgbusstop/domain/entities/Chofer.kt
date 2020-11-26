package com.example.thefalgbusstop.domain.entities


data class Chofer(
    var id: Int,
    var nombre: String,
    var apellido: String,
    var rut: String,
    var nombreCompleto: String = "${nombre.capitalize()} ${apellido.capitalize()}",
    val imgPerfil: String = "https://picsum.photos/60"

)

data class ChoferPost(
    var nombre: String,
    var apellido: String,
    var rut: String,
)

data class ChoferUpdate(
    var id: Int?,
    var nombre: String?,
    var apellido: String?,
    var rut: String?,

    )