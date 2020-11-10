package com.example.thefalgbusstop.presentation.Fragments.Buses

import com.example.thefalgbusstop.data.AgencyDatabase
import com.example.thefalgbusstop.data.LocalBusDataSource
import com.example.thefalgbusstop.data.LocalHorariosDataSource
import com.example.thefalgbusstop.data.LocalPassengerDataSource

class BusRoomDataSource(database: AgencyDatabase
): LocalBusDataSource {
    override fun getBus(BusId: Int): String {
        TODO("Not yet implemented")
    }

}

class HorariosRoomDataSource(
    database: AgencyDatabase
): LocalHorariosDataSource{
    override fun getHorarios(HorariosId: Int): String {
        TODO("Not yet implemented")
    }
}

class PassengerRoomDataSource(
    database: AgencyDatabase
): LocalPassengerDataSource {
    override fun getPassenger(PassengerId: Int): String {
        TODO("Not yet implemented")
    }
}


