package com.example.thefalgbusstop.data

import com.example.thefalgbusstop.domain.entities.*
import io.reactivex.Maybe
import io.reactivex.Single

//HEres

interface RemoteChoferDataSource {
    fun getAllChofers(): Single<List<Chofer>>
    fun getChoferRepo(id: Int): Single<Chofer>
    fun deleteChofer(id: Int): Single<responsePojo>
    fun updateChofer(chofer: ChoferUpdate, id: Int): Single<responsePojo>
    fun createChofer(chofer: ChoferPost):Single<responsePojo>
}
interface RemoteBusDataSource {
    fun getAllBus(): Single<List<Bus>>
//    fun getBus(busId: Int): Single<Bus>
}
interface RemoteSitDataSource {
    fun getAllSit(): Single<List<Sit>>
//    fun getSit(sitId: Int): Single<Sit>
}
interface RemotePassengerDataSource {
    fun getAllPassenger(): Single<List<Passenger>>
//    fun getPassenger(passengerId: Int): Single<Passenger>
}
interface RemoteRouteDataSource {
    fun getAllRoutes(): Single<List<Route>>
//    fun getRoute(routeId: Int): Single<Route>
}
interface RemoteHorariosDataSource {
    fun getAllHorarios(): Single<List<Horarios>>
//    fun getHorarios(horariosId: Int): Single<Horarios>
}

interface LocalAgencyDataSource {

    fun getChofer(choferId: Int): Maybe<ChoferEntity>

    fun getFavoriteChoferStatus(choferId: Int): Maybe<Boolean>

    fun getPassenger(PassengerId: Int): String

    fun getBus(BusId: Int): String

    fun getRoute(RouteId: Int): String

    fun getSit(SitId: Int): String

    fun getHorarios(HorariosId: Int): String

}



