package com.example.thefalgbusstop.data

import android.util.Log
import com.example.thefalgbusstop.data.network.*
import com.example.thefalgbusstop.data.network.ApiConstants.API_KEY
import com.example.thefalgbusstop.data.services.*
import com.example.thefalgbusstop.domain.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//Implementation of Data Source

class ChoferRetrofitDataSource(
    private val choferRequest: ChoferRequest
): RemoteChoferDataSource {

    override fun getAllChofers(): Single<List<Chofer>> {
        return choferRequest
            .getService<ChoferService>()
            .getAllChofers(API_KEY = API_KEY)
            .map(List<ChoferServer>::toChoferDomainList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }


    override fun getChoferRepo(id: Int): Single<Chofer> {
        TODO("Not yet implemented")
    }
}
class BusRetrofitDataSource(
    private val BusRequest: BusRequest
): RemoteBusDataSource {

    override fun getAllBus(): Single<List<Bus>> {
        return BusRequest
            .getService<BusService>()
            .getAllBus(API_KEY = API_KEY)
            .map(List<BusServer>::toBusDomainList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }


}

class PassengerRetrofitDataSource(
    private val PassengerRequest: PassengerRequest
): RemotePassengerDataSource {

    override fun getAllPassenger(): Single<List<Passenger>> {
        return PassengerRequest
            .getService<PassengerService>()
            .getAllPassenger(API_KEY = API_KEY)
            .map(List<PassengerServer>::toPassengerDomainList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }


}
class RouteRetrofitDataSource(
    private val RouteRequest: RouteRequest
): RemoteRouteDataSource {

    override fun getAllRoutes(): Single<List<Route>> {
        return RouteRequest
            .getService<RouteService>()
            .getAllRoutes(API_KEY = API_KEY)
            .map(List<RouteServer>::toRouteDomainList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
class SitRetrofitDataSource(
    private val SitRequest: SitRequest
): RemoteSitDataSource {

    override fun getAllSit(): Single<List<Sit>> {
        return SitRequest
            .getService<SitService>()
            .getAllSit(API_KEY = API_KEY)
            .map(List<SitServer>::toSitDomainList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
class HorariosRetrofitDataSource(
    private val HorarioRequest: HorarioRequest
): RemoteHorariosDataSource {

    override fun getAllHorarios(): Single<List<Horarios>> {
        return HorarioRequest
            .getService<HorarioService>()
            .getAllHorario(API_KEY = API_KEY)
            .map(List<HorarioServer>::toHorarioDomainList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }


}


