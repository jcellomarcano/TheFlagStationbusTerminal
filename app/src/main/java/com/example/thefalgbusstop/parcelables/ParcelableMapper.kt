package com.example.thefalgbusstop.parcelables

import com.example.thefalgbusstop.domain.entities.Chofer


//Chofer Seccion
fun ChoferParcelable.toChoferDomain() = Chofer(
    id,
    name,
    rut,
    lastname,

)

fun Chofer.toChoferParcelable() = ChoferParcelable(
    id,
    name,
    rut,
    lastname,

)

// end Chofer Seccion
