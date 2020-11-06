package com.example.thefalgbusstop.data

import com.example.thefalgbusstop.domain.Chofer

fun List<ChoferEntity>.toChoferDomainList() = map(ChoferEntity::toChoferDomain)

fun ChoferEntity.toChoferDomain() = Chofer(
    id,
    name,
    rut,
    lastname,
)

fun ChoferEntity.toChoferEntity() = ChoferEntity(
    id,
    name,
    rut,
    lastname,
)

