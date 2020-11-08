package com.example.thefalgbusstop.data

import com.example.thefalgbusstop.domain.Chofer
import io.reactivex.Maybe

class ChoferRoomDataSource(
    database: AgencyDatabase
): LocalChoferDataSource {

    private val choferDao by lazy { database.choferDao() }

    override fun getFavoriteChoferStatus(choferId: Int): Maybe<Boolean> {
        TODO("Not yet implemented")
    }

    override fun updateFavoriteChoferStatus(chofer: Chofer): Maybe<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getChofer(choferId: Int): String {
        TODO("Not yet implemented")


    }
}