package com.example.thefalgbusstop.data.repositories

import com.example.thefalgbusstop.data.*
import com.example.thefalgbusstop.domain.*
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

class ChoferRepository(
    private val remoteChoferDataSource: RemoteChoferDataSource,
    private val localChoferDataSource: LocalChoferDataSource
) {

    //region Public Methods

    fun getAllChofers(): Single<List<Chofer>> =
        remoteChoferDataSource.getAllChofers()

    fun getChofer(choferId: Int): String =
        localChoferDataSource.getChofer(choferId)

    fun getChoferRepo(choferId: Int): Single<Chofer> =
        remoteChoferDataSource.getChoferRepo(choferId)

    fun getFavoriteChoferStatus(choferId: Int): Maybe<Boolean> =
        localChoferDataSource.getFavoriteChoferStatus(choferId)

    fun updateFavoriteChoferStatus(chofer: Chofer): Maybe<Boolean> =
        localChoferDataSource.updateFavoriteChoferStatus(chofer)

    //endregion
}
class BusRepository(
    private val remoteBusDataSource: RemoteBusDataSource,
    private val localBusDataSource: LocalBusDataSource
) {

    //region Public Methods

    fun getAllBus(): Single<List<Bus>> =
        remoteBusDataSource.getAllBus()

    fun getChofer(busId: Int): String =
        localBusDataSource.getBus(busId)

    //endregion
}

class SitRepository(
    private val remoteSitDataSource: RemoteSitDataSource,
    private val localSitDataSource: LocalSitDataSource
) {

    //region Public Methods

    fun getAllSit(): Single<List<Sit>> =
        remoteSitDataSource.getAllSit()

    fun getSit(sitId: Int): String =
        localSitDataSource.getSit(sitId)


    //endregion
}

class PassengerRepository(
    private val remotePassengerDataSource: RemotePassengerDataSource,
    private val localPassengerDataSource: LocalPassengerDataSource
) {

    //region Public Methods

    fun getAllPassenger(): Single<List<Passenger>> =
        remotePassengerDataSource.getAllPassenger()

    fun getPassenger(PassengerId: Int): String =
        localPassengerDataSource.getPassenger(PassengerId)

//    fun getFavoritePassengerStatus(PassengerId: Int): Maybe<Boolean> =
//        localChoferDataSource.getFavoriteChoferStatus(PassengerId)
//
//    fun updateFavoritePassengerStatus(Passenger: Passenger): Maybe<Boolean> =
//        localChoferDataSource.updateFavoriteChoferStatus(Passenger)

    //endregion
}
class HorariosRepository(
    private val remoteHorariosDataSource: RemoteHorariosDataSource,
    private val localHorariosDataSource: LocalHorariosDataSource
) {

    //region Public Methods

    fun getAllHorarios(): Single<List<Horarios>> =
        remoteHorariosDataSource.getAllHorarios()

    fun getHorarios(HorariosId: Int): String =
        localHorariosDataSource.getHorarios(HorariosId)

//    fun getFavoriteHorariosStatus(HorariosId: Int): Maybe<Boolean> =
//        localHorariosDataSource.getFavoriteHorariosStatus(HorariosId)
//
//    fun updateFavoriteHorariosStatus(Horarios: Horarios): Maybe<Boolean> =
//        localHorariosDataSource.updateFavoriteChoferStatus(Horarios)

    //endregion
}class RouteRepository(
    private val remoteRouteDataSource: RemoteRouteDataSource,
    private val localRouteDataSource: LocalRouteDataSource
) {

    //region Public Methods

    fun getAllRoute(): Single<List<Route>> =
        remoteRouteDataSource.getAllRoutes()

    fun getRoute(RouteId: Int): String =
        localRouteDataSource.getRoute(RouteId)

//    fun getFavoriteRouteStatus(RouteId: Int): Maybe<Boolean> =
//        localRouteDataSource.getFavoriteRouteStatus(RouteId)
//
//    fun updateFavoriteRouteStatus(Route: Route): Maybe<Boolean> =
//        localRouteDataSource.updateFavoriteRouteStatus(Route)

    //endregion
}