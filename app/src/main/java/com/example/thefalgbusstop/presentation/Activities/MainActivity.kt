package com.example.thefalgbusstop.presentation.Activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.thefalgbusstop.R
import com.example.thefalgbusstop.utils.Cosntants
import com.example.thefalgbusstop.domain.entities.Bus
import com.example.thefalgbusstop.domain.entities.Chofer
import com.example.thefalgbusstop.domain.entities.Horarios
import com.example.thefalgbusstop.domain.entities.Passenger
import com.example.thefalgbusstop.utils.startActivity
import com.example.thefalgbusstop.databinding.MainActivityBinding
import com.example.thefalgbusstop.parcelables.toChoferParcelable
import com.example.thefalgbusstop.presentation.Fragments.Buses.BusesFragment
import com.example.thefalgbusstop.presentation.Fragments.Chofers.List.ChoferListFragment
import com.example.thefalgbusstop.presentation.Fragments.Hours.HoursFragment
import com.example.thefalgbusstop.presentation.Fragments.Passengers.PassengersFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(),
    ChoferListFragment.OnChoferListFragmentListener,
    BusesFragment.OnBusFragmentListener,
    HoursFragment.OnHoursFragmentListener,
    PassengersFragment.OnPassengersFragmentListener,
    NavigationView.OnNavigationItemSelectedListener{
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var toolbar: Toolbar
    private lateinit var  binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        binding.lifecycleOwner = this@MainActivity
        initComponents()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.chofersFragment,R.id.busesFragment, R.id.hoursFragment -> {
                binding.title = item.title.toString()
                return true
            }
            else -> {
                binding.title = "Estacion la Bandera"
                return false
            }

        }
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

    private fun initComponents(){
        toolbar = findViewById(R.id.toolbar)
        setUpNavigation()
    }

    override fun openChoferDetail(chofer: Chofer) {
        startActivity<ItemDetailActivity> {
            putExtra(Cosntants.EXTRA_CHOFER, chofer.toChoferParcelable())
        }
        overridePendingTransition(R.anim.entry, R.anim.exit)
    }

    override fun openBusDetail(Bus: Bus) {
        Log.i("MainActv", "openChoferDetail: Aun no hay pantalla de detalle")
    }

    override fun openHoursDetail(Hours: Horarios) {
        Log.i("MainActv", "openChoferDetail: Aun no hay pantalla de detalle")
    }

    override fun openPassengerDetail(passenger: Passenger) {
        Log.i("MainActv", "openChoferDetail: Aun no hay pantalla de detalle")
    }



}




