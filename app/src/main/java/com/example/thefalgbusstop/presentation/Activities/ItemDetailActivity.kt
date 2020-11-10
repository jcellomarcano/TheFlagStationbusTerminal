package com.example.thefalgbusstop.presentation.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thefalgbusstop.R
import com.example.thefalgbusstop.data.*
import com.example.thefalgbusstop.data.network.ApiConstants.BASE_API_URL
import com.example.thefalgbusstop.data.network.BusRequest
import com.example.thefalgbusstop.data.network.ChoferRequest
import com.example.thefalgbusstop.data.network.HorarioRequest
import com.example.thefalgbusstop.data.network.PassengerRequest
import com.example.thefalgbusstop.data.repositories.ChoferRepository
import com.example.thefalgbusstop.data.repositories.PassengerRepository
import com.example.thefalgbusstop.databinding.ActivityItemDetailBinding
import com.example.thefalgbusstop.presentation.Fragments.Buses.AgencyDataSource

class ItemDetailActivity : AppCompatActivity() {

    //Fields
    private lateinit var  binding: ActivityItemDetailBinding

    private val passengerRequest: PassengerRequest by lazy {
        PassengerRequest(BASE_API_URL)
    }
    private val choferRequest: ChoferRequest by lazy {
        ChoferRequest(BASE_API_URL)
    }

    private val busRequest: BusRequest by lazy {
        BusRequest(BASE_API_URL)
    }

    private val hoursRequest: HorarioRequest by lazy{
        HorarioRequest(BASE_API_URL)
    }
    //passenger
    private val localPassengerDataSource : LocalAgencyDataSource by lazy{
        AgencyDataSource(AgencyDatabase.getDatabase(applicationContext))
    }

    private val remotePassengerDataSource: RemotePassengerDataSource by lazy {
        PassengerRetrofitDataSource(passengerRequest)
    }

    private val passengerRepository: PassengerRepository by lazy {
        PassengerRepository(remotePassengerDataSource, localPassengerDataSource)
    }
    //endPassenger

    //Chofer
    private val remoteChoferDataSource: RemoteChoferDataSource by lazy {
        ChoferRetrofitDataSource(choferRequest)
    }

    private val localAgencyDataSource : LocalAgencyDataSource by lazy{
        AgencyDataSource(AgencyDatabase.getDatabase(applicationContext))
    }

    private val choferRepository: ChoferRepository by lazy {
        ChoferRepository(remoteChoferDataSource, localAgencyDataSource)
    }

    //endChofer






    //end Fields


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
    }
}