package com.example.thefalgbusstop.data.repositories

import android.util.Log
import com.example.thefalgbusstop.data.*
import com.example.thefalgbusstop.domain.entities.*
import io.reactivex.Maybe
import io.reactivex.Single


class ChoferRepository(
    private val remoteChoferDataSource: RemoteChoferDataSource,
    private val localAgencyyDataSource: LocalAgencyDataSource,
) {

    //region Public Methods

    fun getAllChofers(): Single<List<Chofer>> =
        remoteChoferDataSource.getAllChofers()

    fun getChofer(choferId: Int): String =
        localAgencyyDataSource.getChofer(choferId).toString()

    fun getChoferRepo(choferId: Int): Single<Chofer> =
        remoteChoferDataSource.getChoferRepo(choferId)

    fun getFavoriteChoferStatus(choferId: Int): Maybe<Boolean> =
        localAgencyyDataSource.getFavoriteChoferStatus(choferId)

    fun createChofer(chofer: ChoferPost): Single<responsePojo> =
        remoteChoferDataSource.createChofer(chofer)

    fun deleteChofer(id: Int) {
        remoteChoferDataSource.deleteChofer(id)
        Log.i("Repository", "deleteChofer: " + remoteChoferDataSource.deleteChofer(id))
    }

    //endregion
}
class BusRepository(
    private val remoteBusDataSource: RemoteBusDataSource,
    private val localAgencytaSource: LocalAgencyDataSource,
) {

    //region Public Methods

    fun getAllBus(): Single<List<Bus>> =
        remoteBusDataSource.getAllBus()
    fun getChofer(busId: Int): String =
        localAgencytaSource.getBus(busId)
    //endregion
}

class SitRepository(
    private val remoteSitDataSource: RemoteSitDataSource,
    private val localAgencytaSource: LocalAgencyDataSource,
) {

    //region Public Methods
    fun getAllSit(): Single<List<Sit>> =
        remoteSitDataSource.getAllSit()
    fun getSit(sitId: Int): String =
        localAgencytaSource.getSit(sitId)
    //endregion
}

class PassengerRepository(
    private val remotePassengerDataSource: RemotePassengerDataSource,
    private val localAgencyngerDataSource: LocalAgencyDataSource,
) {


    //region Public Methods

    fun getAllPassenger(): Single<List<Passenger>> =
        remotePassengerDataSource.getAllPassenger()

    fun getPassenger(PassengerId: Int): String =
        localAgencyngerDataSource.getPassenger(PassengerId)

//    fun getFavoritePassengerStatus(PassengerId: Int): Maybe<Boolean> =
//        localAgencyrDataSource.getFavoriteChoferStatus(PassengerId)
//
//    fun updateFavoritePassengerStatus(Passenger: Passenger): Maybe<Boolean> =
//        localAgencyrDataSource.updateFavoriteChoferStatus(Passenger)

    //endregion
}
class HorariosRepository(
    private val remoteHorariosDataSource: RemoteHorariosDataSource,
    private val localAgencyiosDataSource: LocalAgencyDataSource,
) {

    //region Public Methods

    fun getAllHorarios(): Single<List<Horarios>> =
        remoteHorariosDataSource.getAllHorarios()

    fun getHorarios(HorariosId: Int): String =
        localAgencyiosDataSource.getHorarios(HorariosId)

//    fun getFavoriteHorariosStatus(HorariosId: Int): Maybe<Boolean> =
//        localAgencyiosDataSource.getFavoriteHorariosStatus(HorariosId)
//
//    fun updateFavoriteHorariosStatus(Horarios: Horarios): Maybe<Boolean> =
//        localAgencyiosDataSource.updateFavoriteChoferStatus(Horarios)

    //endregion
}class RouteRepository(
    private val remoteRouteDataSource: RemoteRouteDataSource,
    private val localAgencyDataSource: LocalAgencyDataSource,
) {

    //region Public Methods

    fun getAllRoute(): Single<List<Route>> =
        remoteRouteDataSource.getAllRoutes()

    fun getRoute(RouteId: Int): String =
        localAgencyDataSource.getRoute(RouteId)

//    fun getFavoriteRouteStatus(RouteId: Int): Maybe<Boolean> =
//        localAgencyDataSource.getFavoriteRouteStatus(RouteId)
//
//    fun updateFavoriteRouteStatus(Route: Route): Maybe<Boolean> =
//        localAgencyDataSource.updateFavoriteRouteStatus(Route)

    //endregion
}