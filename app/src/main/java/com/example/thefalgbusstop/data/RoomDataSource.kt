package com.example.thefalgbusstop.presentation.fragments.Buses

import com.example.thefalgbusstop.data.*
import io.reactivex.Maybe


class AgencyDataSource(database: AgencyDatabase
): LocalAgencyDataSource {

    private val agencyDao by lazy { database.agencyDao() }

    override fun getBus(BusId: Int): String {
        TODO("Not yet implemented")
    }
    override fun getHorarios(HorariosId: Int): String {
        TODO("Not yet implemented")
    }

    override fun getPassenger(PassengerId: Int): String {
        TODO("Not yet implemented")
    }

    override fun getChofer(choferId: Int): Maybe<ChoferEntity> {
        return agencyDao.getChoferById(choferId)
//            .observeOn(AndroidSchedulers.mainThread())
//            .onErrorReturn {  }
//            .subscribeOn(Scheduler.io())

    }

    override fun getFavoriteChoferStatus(choferId: Int): Maybe<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getRoute(RouteId: Int): String {
        TODO("Not yet implemented")
    }

    override fun getSit(SitId: Int): String {
        TODO("Not yet implemented")
    }
}


