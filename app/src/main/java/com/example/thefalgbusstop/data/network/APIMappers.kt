package com.example.thefalgbusstop.data.network

import android.util.Log
import com.example.thefalgbusstop.domain.Chofer

fun List<ChoferServer>.toChoferDomainList(): List<Chofer> = map {
    it.run{
        Chofer(
            id,
            name,
            lastname,
            rut
        )
    }
}