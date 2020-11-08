package com.example.thefalgbustop.presentation.Fragments.Buses

import com.example.thefalgbusstop.data.AgencyDatabase
import com.example.thefalgbusstop.data.LocalBusDataSource

class BusRoomDataSource(database: AgencyDatabase
): LocalBusDataSource {
    override fun getBus(BusId: Int): String {
        TODO("Not yet implemented")
    }

}
