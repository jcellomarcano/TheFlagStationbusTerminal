package com.example.thefalgbusstop.presentation.Activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.thefalgbusstop.R
import com.example.thefalgbusstop.Utils.Cosntants
import com.example.thefalgbusstop.data.network.ApiConstants.BASE_API_URL
import com.example.thefalgbusstop.data.network.ChoferRequest
import com.example.thefalgbusstop.domain.Bus
import com.example.thefalgbusstop.domain.Chofer
import com.example.thefalgbusstop.domain.Horarios
import com.example.thefalgbusstop.domain.Passenger
import com.example.thefalgbusstop.Utils.startActivity
import com.example.thefalgbusstop.parcelables.toChoferrParcelable
import com.example.thefalgbusstop.presentation.Fragments.Buses.BusesFragment
import com.example.thefalgbusstop.presentation.Fragments.Chofers.List.ChoferListFragment
import com.example.thefalgbusstop.presentation.Fragments.Hours.HoursFragment
import com.example.thefalgbusstop.presentation.Fragments.Passengers.PassengersFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.System.exit


class MainActivity : AppCompatActivity(),
    ChoferListFragment.OnChoferListFragmentListener,
    BusesFragment.OnBusFragmentListener,
    HoursFragment.OnHoursFragmentListener,
    PassengersFragment.OnPassengersFragmentListener{
    lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setUpNavigation()
        initComponents()
    }


    fun setUpNavigation(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(
                bottomNavigationView,
                navHostFragment.navController
        )
    }
    private val choferRequest: ChoferRequest by lazy {
        ChoferRequest(BASE_API_URL)
    }
    private fun initComponents(){
        //TODO:implementar
    }

    override fun openChoferDetail(chofer: Chofer) {
        Log.i("MainActv", "openChoferDetail: Aun no hay pantalla de detalle")
        startActivity<ItemDetailActivity> {
            putExtra(Cosntants.EXTRA_CHOFER, chofer.toChoferrParcelable())
        }
        overridePendingTransition(R.anim.entry, R.anim.exit)
    }

    override fun openBusDetail(Bus: Bus) {
        TODO("Not yet implemented")
    }

    override fun openHoursDetail(Hours: Horarios) {
        TODO("Not yet implemented")
    }

    override fun openPassengerDetail(passenger: Passenger) {
        TODO("Not yet implemented")
    }


}



