package com.example.thefalgbusstop.presentation.Activities

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.thefalgbusstop.R
import com.example.thefalgbusstop.utils.Cosntants
import com.example.thefalgbusstop.utils.Event
import com.example.thefalgbusstop.utils.getViewModel
import com.example.thefalgbusstop.utils.showLongToast
import com.example.thefalgbusstop.data.AgencyDatabase
import com.example.thefalgbusstop.data.ChoferRetrofitDataSource
import com.example.thefalgbusstop.data.LocalAgencyDataSource
import com.example.thefalgbusstop.data.RemoteChoferDataSource
import com.example.thefalgbusstop.data.network.ApiConstants.BASE_API_URL
import com.example.thefalgbusstop.data.network.ChoferRequest
import com.example.thefalgbusstop.data.repositories.ChoferRepository
import com.example.thefalgbusstop.databinding.ActivityEditItemBinding
import com.example.thefalgbusstop.domain.ChofersUseCase
import com.example.thefalgbusstop.domain.entities.Chofer
import com.example.thefalgbusstop.parcelables.ChoferParcelable
import com.example.thefalgbusstop.parcelables.toChoferDomain
import com.example.thefalgbusstop.presentation.Activities.EditItemActivityViewModel.*
import com.example.thefalgbusstop.presentation.Activities.EditItemActivityViewModel.UserEditNavigation.*
import com.example.thefalgbusstop.presentation.Fragments.Buses.AgencyDataSource
import kotlinx.android.synthetic.main.activity_edit_item.*
import java.util.*

class EditItemActivity : AppCompatActivity() {

    //Global Val
    private lateinit var binding: ActivityEditItemBinding
    private var isEdit: Boolean = false

    //Global Chofer
    private val choferRequest: ChoferRequest by lazy {
        ChoferRequest(BASE_API_URL)
    }
    private val remoteChoferDataSource: RemoteChoferDataSource by lazy {
        ChoferRetrofitDataSource(choferRequest)
    }

    private val localAgencyDataSource : LocalAgencyDataSource by lazy{
        AgencyDataSource(AgencyDatabase.getDatabase(applicationContext))
    }

    val choferRepository: ChoferRepository by lazy {
        ChoferRepository(remoteChoferDataSource, localAgencyDataSource)
    }

    private val choferUseCase: ChofersUseCase by lazy{
        ChofersUseCase(choferRepository)
    }

    private val editItemActivityViewModel: EditItemActivityViewModel by lazy {
        getViewModel {
            EditItemActivityViewModel(
                intent.getParcelableExtra<ChoferParcelable>(Cosntants.EXTRA_CHOFER)
                    ?.toChoferDomain(),
                choferUseCase
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor =
                ContextCompat.getColor(this@EditItemActivity, R.color.white)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        initComponents()
    }

    private fun initComponents(){

        //Binding initialization
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_item)
        binding.lifecycleOwner = this@EditItemActivity
        isEdit = true
        if(isEdit){
            binding.edtTextId.visibility = View.VISIBLE
            binding.spnEntity.visibility = View.GONE
            binding.imgPage.setImageDrawable(ContextCompat.getDrawable(this@EditItemActivity, R.drawable.ic_edit))

        } else {
            binding.edtTextId.visibility = View.GONE
            binding.imgPage.setImageDrawable(ContextCompat.getDrawable(this@EditItemActivity, R.drawable.ic_add_user))
        }
        binding.spnEntity.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long,
            ) {
                val selectedText = parent.getChildAt(0) as TextView
                selectedText.setTextColor(ContextCompat.getColor(applicationContext,
                    R.color.grey_text_color))

                binding.btnConfirm.isEnabled = parent.selectedItemPosition != 0
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        editItemActivityViewModel.choferValues.observe(this, Observer(this::loadChofer))
        editItemActivityViewModel.events.observe(this, Observer(this::validateEvents))
        editItemActivityViewModel.onUserValidation()
        val adapter = ArrayAdapter.createFromResource(applicationContext,
            R.array.entityType, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnEntity.adapter = adapter
        btnBack.setOnClickListener { onBackPressed() }
        btnConfirm.setOnClickListener {
            editItemActivityViewModel.showWarningDialog(this@EditItemActivity)  }


    }

    private fun loadChofer(chofer: Chofer){
        if(isEdit){
            binding.pageTitle.text = "Editar Chofer"
            binding.id = "Id del chofer: ${chofer.id}"
            binding.string1 = chofer.name.capitalize(Locale.ROOT)
            binding.string2 = chofer.lastname.capitalize(Locale.ROOT)
            binding.string3 = chofer.rut

        } else {
            binding.pageTitle.text = "Agregar Chofer"
        }
        binding.hintString1 = getString(R.string.nameTag)
        binding.hintString2 = getString(R.string.lastnameTag)
        binding.hintString3 = getString(R.string.rutTag)
        binding.editTextNumber.visibility = View.GONE
        binding.editTextNumber2.visibility = View.GONE
        binding.editTextNumber3.visibility = View.GONE

    }

    private fun validateEvents(event: Event<UserEditNavigation>){
        event.getContentIfNotHandled()?.let { navigation ->
            when (navigation) {
                is ShowError -> navigation.run {
                    this@EditItemActivity.showLongToast("Lo sentimos, no podemos establecer conexion -> ${error.message}")
                }
                CloseActivity -> {
                    this@EditItemActivity.showLongToast(R.string.error_no_character_data)
                    finish()
                }
            }
        }
    }
}