package com.example.thefalgbusstop.presentation.Activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.thefalgbusstop.R
import com.example.thefalgbusstop.utils.*
import com.example.thefalgbusstop.data.*
import com.example.thefalgbusstop.data.network.ApiConstants.BASE_API_URL
import com.example.thefalgbusstop.data.network.BusRequest
import com.example.thefalgbusstop.data.network.ChoferRequest
import com.example.thefalgbusstop.data.network.HorarioRequest
import com.example.thefalgbusstop.data.network.PassengerRequest
import com.example.thefalgbusstop.data.repositories.ChoferRepository
import com.example.thefalgbusstop.data.repositories.PassengerRepository
import com.example.thefalgbusstop.databinding.ActivityItemDetailBinding
import com.example.thefalgbusstop.domain.entities.Chofer
import com.example.thefalgbusstop.parcelables.ChoferParcelable
import com.example.thefalgbusstop.parcelables.toChoferDomain
import com.example.thefalgbusstop.presentation.Fragments.Buses.AgencyDataSource
import com.example.thefalgbusstop.presentation.Activities.ItemDetailActivityViewModel.UserDetailNavigation
import com.example.thefalgbusstop.presentation.Activities.ItemDetailActivityViewModel.UserDetailNavigation.*
import androidx.lifecycle.Observer
import com.example.thefalgbusstop.domain.ChofersUseCase
import kotlinx.android.synthetic.main.activity_item_detail.*

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

    val choferRepository: ChoferRepository by lazy {
        ChoferRepository(remoteChoferDataSource, localAgencyDataSource)
    }

    private val chofersUseCase: ChofersUseCase by lazy {
        ChofersUseCase(choferRepository)
    }

    private val itemDetailActivityViewModel: ItemDetailActivityViewModel by lazy {
        getViewModel {
            ItemDetailActivityViewModel(
                null,
                intent.getParcelableExtra<ChoferParcelable>(Cosntants.EXTRA_CHOFER)?.toChoferDomain()
            )
        }
    }


    //endChofer

    //end Fields

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent()
    }

    private fun initComponent(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_item_detail)
        binding.lifecycleOwner = this@ItemDetailActivity
        deleteBtn.setOnClickListener { itemDetailActivityViewModel.deleteChofer(this@ItemDetailActivity) }
        editBtn.setOnClickListener {itemDetailActivityViewModel.openEditChofer(this@ItemDetailActivity)}
        itemDetailActivityViewModel.choferValues.observe(this, Observer(this::loadChofer))
        itemDetailActivityViewModel.events.observe(this, Observer(this::validateEvents))
        itemDetailActivityViewModel.onUserValidation()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadChofer(chofer: Chofer){
        binding.characterImage.bindCircularImageUrl(
            url = chofer.profileImg,
            placeholder = R.drawable.ic_camera_alt_black,
            errorPlaceholder = R.drawable.ic_broken_image_black
        )
        binding.nameEntity = chofer.fullname
        binding.descId = chofer.rut
        binding.idEntity = chofer.id.toString()
        binding.addInfo1 = chofer.rut

    }




    private fun validateEvents(event: Event<UserDetailNavigation>?) {
        event?.getContentIfNotHandled()?.let { navigation ->
            when (navigation) {
                is ShowEpisodeError -> navigation.run {
                    this@ItemDetailActivity.showLongToast("Error -> ${error.message}")
                }
                CloseActivity -> {
                    this@ItemDetailActivity.showLongToast(R.string.error_no_character_data)
                    finish()
                }
            }
        }
    }
}

