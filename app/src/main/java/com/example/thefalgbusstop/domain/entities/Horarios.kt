package com.example.thefalgbusstop.domain.entities


data class Horarios(
    val id: Int,
    val fecha: String,
    val hora: String,
    val idTrayecto: Int,
    val idBus: Int,
) {
    override fun toString(): String {
        return "Salida: $fecha $hora"
    }

}
