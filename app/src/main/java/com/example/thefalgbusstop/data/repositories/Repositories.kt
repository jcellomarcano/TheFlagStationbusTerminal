package com.example.thefalgbusstop.data.repositories

import com.example.thefalgbusstop.data.ChoferEntity
import com.example.thefalgbusstop.data.LocalChoferDataSource
import com.example.thefalgbusstop.data.RemoteBusDataSource
import com.example.thefalgbusstop.data.RemoteChoferDataSource
import com.example.thefalgbusstop.domain.Bus
import com.example.thefalgbusstop.domain.Chofer
import com.example.thefalgbusstop.domain.Sit
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

    fun getChofer(choferId: Int): Single<Chofer> =
        remoteChoferDataSource.getChofer(choferId)

    fun getFavoriteChoferStatus(choferId: Int): Maybe<Boolean> =
        localChoferDataSource.getFavoriteChoferStatus(choferId)

    fun updateFavoriteChoferStatus(chofer: Chofer): Maybe<Boolean> =
        localChoferDataSource.updateFavoriteChoferStatus(chofer)

    //endregion
}
class BusRepository(
    private val remoteBusDataSource: RemoteBusDataSource,
//    private val localBusDataSource: LocalBusDataSource
) {

    //region Public Methods

    fun getAllBus(): Single<List<Bus>> =
        remoteBusDataSource.getAllBus()

    fun getChofer(busId: Int): Single<Bus> =
        remoteBusDataSource.getBus(busId)

    //endregion
}

class SitRepository(
    private val remoteSitDataSource: RemoteSitDataSource,
//    private val localSitDataSource: LocalSitDataSource
) {

    //region Public Methods

    fun getAllSit(): Single<List<Sit>> =
        remoteSitDataSource.getAllChofers()

    fun getSit(sitId: Int): Single<Sit> =
        remoteSitDataSource.getChofer(sitId)


    //endregion
}

class ChoferRepository(
    private val remoteChoferDataSource: RemoteChoferDataSource,
//    private val localChoferDataSource: LocalChoferDataSource
) {

    //region Public Methods

    fun getAllChofers(): Single<List<Chofer>> =
        remoteChoferDataSource.getAllChofers()

    fun getChofer(choferId: Int): Single<Chofer> =
        remoteChoferDataSource.getChofer(choferId)

    fun getFavoriteChoferStatus(choferId: Int): Maybe<Boolean> =
        localChoferDataSource.getFavoriteChoferStatus(choferId)

    fun updateFavoriteChoferStatus(chofer: Chofer): Maybe<Boolean> =
        localChoferDataSource.updateFavoriteChoferStatus(chofer)

    //endregion
}class ChoferRepository(
    private val remoteChoferDataSource: RemoteChoferDataSource,
//    private val localChoferDataSource: LocalChoferDataSource
) {

    //region Public Methods

    fun getAllChofers(): Single<List<Chofer>> =
        remoteChoferDataSource.getAllChofers()

    fun getChofer(choferId: Int): Single<Chofer> =
        remoteChoferDataSource.getChofer(choferId)

    fun getFavoriteChoferStatus(choferId: Int): Maybe<Boolean> =
        localChoferDataSource.getFavoriteChoferStatus(choferId)

    fun updateFavoriteChoferStatus(chofer: Chofer): Maybe<Boolean> =
        localChoferDataSource.updateFavoriteChoferStatus(chofer)

    //endregion
}class ChoferRepository(
    private val remoteChoferDataSource: RemoteChoferDataSource,
//    private val localChoferDataSource: LocalChoferDataSource
) {

    //region Public Methods

    fun getAllChofers(): Single<List<Chofer>> =
        remoteChoferDataSource.getAllChofers()

    fun getChofer(choferId: Int): Single<Chofer> =
        remoteChoferDataSource.getChofer(choferId)

    fun getFavoriteChoferStatus(choferId: Int): Maybe<Boolean> =
        localChoferDataSource.getFavoriteChoferStatus(choferId)

    fun updateFavoriteChoferStatus(chofer: Chofer): Maybe<Boolean> =
        localChoferDataSource.updateFavoriteChoferStatus(chofer)

    //endregion
}class ChoferRepository(
    private val remoteChoferDataSource: RemoteChoferDataSource,
//    private val localChoferDataSource: LocalChoferDataSource
) {

    //region Public Methods

    fun getAllChofers(): Single<List<Chofer>> =
        remoteChoferDataSource.getAllChofers()

    fun getChofer(choferId: Int): Single<Chofer> =
        remoteChoferDataSource.getChofer(choferId)

    fun getFavoriteChoferStatus(choferId: Int): Maybe<Boolean> =
        localChoferDataSource.getFavoriteChoferStatus(choferId)

    fun updateFavoriteChoferStatus(chofer: Chofer): Maybe<Boolean> =
        localChoferDataSource.updateFavoriteChoferStatus(chofer)

    //endregion
}