package com.example.thefalgbusstop.parcelables

import com.example.thefalgbusstop.domain.Chofer

fun ChoferParcelable.toChoferDomain() = Chofer(
    id,
    name,
    rut,
    lastname,

)

fun Chofer.toChoferrParcelable() = ChoferParcelable(
    id,
    name,
    rut,
    lastname,

)