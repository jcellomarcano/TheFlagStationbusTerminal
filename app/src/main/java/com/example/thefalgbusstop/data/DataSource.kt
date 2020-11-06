package com.example.thefalgbusstop.data

import com.example.thefalgbusstop.domain.*
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

//HEres

interface RemoteChoferDataSource {
    fun getAllChofers(): Single<List<Chofer>>
    fun getChofer(choferId: Int): Single<Chofer>
}
interface RemoteBusDataSource {
    fun getAllBus(): Single<List<Bus>>
    fun getBus(busId: Int): Single<Bus>
}
interface RemoteSitDataSource {
    fun getAllSit(): Single<List<Sit>>
    fun getSit(sitId: Int): Single<Sit>
}
interface RemotePassengerDataSource {
    fun getAllPassenger(): Single<List<Passenger>>
    fun getPassenger(passengerId: Int): Single<Passenger>
}
interface RemoteRouteDataSource {
    fun getAllRoutes(): Single<List<Route>>
    fun getRoute(routeId: Int): Single<Route>
}
interface RemoteHorariosDataSource {
    fun getAllHorarios(): Single<List<Horarios>>
    fun getHorarios(horariosId: Int): Single<Horarios>
}

interface LocalChoferDataSource {

    fun getChofer(choferId: Int): String

    fun getFavoriteChoferStatus(choferId: Int): Maybe<Boolean>

    fun updateFavoriteChoferStatus(chofer: Chofer): Maybe<Boolean>
}