package com.example.thefalgbusstop.domain.entities

data class Bus(
    val id: Int,
    val patente: String,
    val marca: String,
    val choferId: Int,
    val busImg: String = "https://cms-assets.tutsplus.com/uploads/users/158/posts/24850/preview_image/busPreview.jpg"
) {
    override fun toString(): String {
        return "Bus(choferId=$choferId)"
    }
}