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
    nombre,
    rut,
    apellido,

)

// end Chofer Seccion
