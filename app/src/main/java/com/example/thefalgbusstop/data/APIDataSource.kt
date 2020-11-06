package com.example.thefalgbusstop.data

import android.util.Log
import com.example.thefalgbusstop.data.network.ApiConstants.API_KEY
import com.example.thefalgbusstop.data.network.ChoferRequest
import com.example.thefalgbusstop.data.network.ChoferResponseServer
import com.example.thefalgbusstop.data.network.ChoferServer
import com.example.thefalgbusstop.data.network.toChoferDomainList
import com.example.thefalgbusstop.data.services.ChoferService
import com.example.thefalgbusstop.domain.Chofer
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


}