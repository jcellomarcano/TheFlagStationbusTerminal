package com.example.thefalgbusstop.data.network


import com.example.thefalgbusstop.domain.*

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

fun List<BusServer>.toBusDomainList(): List<Bus> = map {
    it.run{
        Bus(
            id,
            patente,
            marca,
            choferId
        )
    }
}

fun List<PassengerServer>.toPassengerDomainList(): List<Passenger> = map {
    it.run{
        Passenger(
            id,
            name,
            lastname,
            rut
        )
    }
}

fun List<RouteServer>.toRouteDomainList(): List<Route> = map {
    it.run{
        Route(
            id,
            ida,
            vuelta,
            terminal
        )
    }
}

fun List<SitServer>.toSitDomainList(): List<Sit> = map {
    it.run{
        Sit(
            id,
            numAsiento,
            idBus,
            idPasenger
        )
    }
}

fun List<HorarioServer>.toHorarioDomainList(): List<Horarios> = map {
    it.run{
        Horarios(
            id,
            fecha,
            hora,
            idTrayecto,
            idBus
        )
    }
}



fun ChoferServer.toChoferDomain() = Chofer(
            id,
            name,
            lastname,
            rut
        )


