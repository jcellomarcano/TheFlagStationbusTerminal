package com.example.thefalgbusstop.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.thefalgbusstop.R
import com.example.thefalgbusstop.databinding.MainActivityBinding
import com.example.thefalgbusstop.domain.entities.Bus
import com.example.thefalgbusstop.domain.entities.Chofer
import com.example.thefalgbusstop.domain.entities.Horarios
import com.example.thefalgbusstop.domain.entities.Passenger
import com.example.thefalgbusstop.parcelables.toChoferParcelable
import com.example.thefalgbusstop.presentation.fragments.Buses.BusesFragment
import com.example.thefalgbusstop.presentation.fragments.Chofers.List.ChoferListFragment
import com.example.thefalgbusstop.presentation.fragments.Hours.HoursFragment
import com.example.thefalgbusstop.presentation.fragments.Passengers.PassengersFragment
import com.example.thefalgbusstop.utils.Cosntants
import com.example.thefalgbusstop.utils.Utils
import com.example.thefalgbusstop.utils.startActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity(),
    ChoferListFragment.OnChoferListFragmentListener,
    BusesFragment.OnBusFragmentListener,
    HoursFragment.OnHoursFragmentListener,
    PassengersFragment.OnPassengersFragmentListener,
    NavigationView.OnNavigationItemSelectedListener{
    lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var  binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        binding.lifecycleOwner = this@MainActivity
        initComponents()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.chofersFragment, R.id.busesFragment, R.id.hoursFragment -> {
                binding.title = item.title.toString()
                true
            }
            else -> {
                binding.title = "Estacion la Bandera"
                false
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.i("MAin", "onCreateOptionsMenu: estamos en el menu")
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    //Handling Action Bar button click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        Log.i("MAin", "onOptionsItemSelected: ${item.itemId}")
        val id = item.itemId
        Log.i("MAin", "onOptionsItemSelected: ${item.itemId}")
        return when (id) {
            R.id.add_entity -> {
                Toast.makeText(this, "clicl en agregar", Toast.LENGTH_SHORT).show()
                addEntity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun setUpNavigation(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(
            bottomNavigationView,
            navHostFragment.navController

        )
    }

    private fun initComponents(){
        title = "Terminal la Bandera App"
        setUpNavigation()
    }

    private fun addEntity(){
        val intent = Intent(this, EditItemActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.entry, R.anim.exit)
    }



    override fun openChoferDetail(chofer: Chofer) {
        startActivity<ItemDetailActivity> {
            putExtra(Cosntants.EXTRA_CHOFER, chofer.toChoferParcelable())
        }
        overridePendingTransition(R.anim.entry, R.anim.exit)
    }

    override fun openBusDetail(Bus: Bus) {
        notYetImplementDialog()
    }

    override fun openHoursDetail(Hours: Horarios) {
        notYetImplementDialog()
    }

    override fun openPassengerDetail(passenger: Passenger) {
        notYetImplementDialog()
    }

    private fun notYetImplementDialog(){
        val nDialog = Utils.confirmDialof(
            this,
            "En Desarrollo",
            "Estamos trabajando para desarrollar todos estos features, proximamente se notificará",
            "Continuar"
        )
        nDialog.setConfirmClickListener {
            nDialog.dismissWithAnimation()
        }
        nDialog.show()
    }



}




