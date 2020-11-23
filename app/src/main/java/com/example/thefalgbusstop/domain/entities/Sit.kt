package com.example.thefalgbusstop.domain.entities

data class Sit(
    val id: Int,
    val numAsiento: Int,
    val idBus: Int,
    val idPasenger: Int,
)