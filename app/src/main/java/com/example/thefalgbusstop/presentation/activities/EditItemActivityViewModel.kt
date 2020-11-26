package com.example.thefalgbusstop.presentation.activities

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.thefalgbusstop.utils.Event
import com.example.thefalgbusstop.utils.Utils
import com.example.thefalgbusstop.domain.ChofersUseCase
import android.content.Intent
import com.example.thefalgbusstop.domain.entities.*
import com.example.thefalgbusstop.presentation.activities.EditItemActivityViewModel.UserEditNavigation.*
import io.reactivex.disposables.CompositeDisposable
import java.lang.Exception

class EditItemActivityViewModel(
    private val chofer: Chofer? = null,
    private val chofersUseCase: ChofersUseCase? = null,
    private val passenger: Passenger? = null,

    ): ViewModel() {

    private val disposable = CompositeDisposable()
    private lateinit var alertDialog: SweetAlertDialog

    //Init and databinding parse

    private var misEdit: Boolean = false
    private var createChofer = ChoferPost("","","")
    private var updateChofer = ChoferUpdate(chofer!!.id,chofer.nombre,chofer.apellido,chofer.rut)
    init {
        misEdit = chofer != null
        Log.i("EditVM", "init: $misEdit ")
    }

    //Chofer Values

    private  val _choferValues = MutableLiveData<Chofer>()
    val choferValues: LiveData<Chofer> get() = _choferValues


    //Passenger Values
    private val _passengerValues = MutableLiveData<Passenger>()
    val passengerValues: LiveData<Passenger> get() = _passengerValues

    //Events
    private val _events = MutableLiveData<Event<UserEditNavigation>>()
    val events: LiveData<Event<UserEditNavigation>> get() = _events

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    //PubMeth
    fun onUserValidation(){
        if (chofer == null && passenger == null){
            _events.value = Event(CloseActivity)
            return
        }
        else if (chofer != null && passenger == null){
            _choferValues.value = chofer
        }
        else if (chofer == null && passenger != null){
            _passengerValues.value = passenger
        }
        else
            _events.value = Event(CloseActivity)
    }

    fun onDisplayEditTextConfirm(activity: EditItemActivity,
                                 string1: String,
                                 string2: String,
                                 string3: String,
                                 isEdit: Boolean){
        misEdit = isEdit
        try {
            if(misEdit){
                updateChofer.id = chofer!!.id
                updateChofer.nombre = string1
                updateChofer.apellido = string2
                updateChofer.rut = string3

                Log.i("EDITVM", "onDisplayEditTextConfirm: $string1")
                Log.i("EDITVM", "onDisplayEditTextConfirm: ${updateChofer.nombre}")
                Log.i("EDITVM", "onDisplayEditTextConfirm: ${updateChofer.apellido}")
                Log.i("EDITVM", "onDisplayEditTextConfirm: ${updateChofer.rut}")
            }else {
                createChofer.nombre = string1
                createChofer.apellido = string2
                createChofer.rut = string3
                Log.i("EDITVM", "onDisplayEditTextConfirm: ${createChofer.nombre}")
                Log.i("EDITVM", "onDisplayEditTextConfirm: ${createChofer.apellido}")
                Log.i("EDITVM", "onDisplayEditTextConfirm: ${createChofer.rut}")
            }

            showWarningDialog(activity)


        }catch (e: Exception){
            Log.i("EditVM", "onDisplayEditTextConfirm: $e")
        }

    }

    // end PubMeth

    private fun doCreateEntity(activity: EditItemActivity){
        //Dialog View
        val alertDialogLoading = Utils.progressDialog(
            activity,
            "Enviando"
        )
        if (chofersUseCase != null) {
            disposable.add(
                chofersUseCase
                    .invokeCreate(createChofer)
                    .doOnSubscribe { alertDialogLoading.show() }
                    .subscribe({ response ->
                        if (response != null) {
                            alertDialogLoading.hide()
                            showConfirmPost(activity, response.message)
                        }
                    }, { error ->
                        alertDialogLoading.hide()
                        errorRequest(activity, error.message.toString())
                        Log.e("editVM", "doEditToServer: a $error")
                        Log.i("editVM", "doEditToServer: a ${error.localizedMessage}")
                        Log.e("editVM", "doEditToServer: a ${error.printStackTrace()}")
                    })
            )
        }
    }

    private fun doEditEntity(activity: EditItemActivity){
        val alertDialogLoading = Utils.progressDialog(
            activity,
            "Enviando"
        )
        Log.i("EDITVM", "doEditEntity: ${updateChofer.nombre}")
        if (chofersUseCase != null) {
            disposable.add(
                chofersUseCase
                    .invokeUpdate(updateChofer, updateChofer.id!!)
                    .doOnSubscribe { alertDialogLoading.show() }
                    .subscribe({ response ->
                        if (response != null) {
                            alertDialogLoading.hide()
                            showConfirmPost(activity, response.message)
                        }
                    }, { error ->
                        alertDialogLoading.hide()
                        errorRequest(activity, error.message.toString())
                        Log.e("editVM", "doEditToServer: a $error")
                        Log.i("editVM", "doEditToServer: a ${error.localizedMessage}")
                        Log.e("editVM", "doEditToServer: a ${error.printStackTrace()}")
                    })
            )
        }
    }


    private fun showConfirmPost(activity: EditItemActivity, response: String){
        val confirmAlertDialog = Utils.confirmDialof(
            activity,
            "Acción Exitosa",
            response,
            "Continuar"
        )
        confirmAlertDialog.setConfirmClickListener {
            confirmAlertDialog.dismissWithAnimation()
            _choferValues.value!!.nombre = updateChofer.nombre!!
            _choferValues.value!!.apellido = updateChofer.apellido!!
            _choferValues.value!!.rut = updateChofer.rut!!
            val intent = Intent(activity, MainActivity::class.java).apply {
            }
            activity.startActivity(intent)
            activity.finish()

        }
        confirmAlertDialog.show()
    }

    private fun errorRequest(activity: EditItemActivity, error: String){
        val errorDialog = Utils.errorAlertDialog(
            activity,
            "Error en el proceso",
            error,
            "continuar",
            false,
            false,
        )
        errorDialog.setConfirmClickListener {
            val intent = Intent(activity, MainActivity::class.java).apply {
            }
            activity.startActivity(intent)
            activity.finish()
        }
        errorDialog.show()
    }

    private fun showWarningDialog(activity: EditItemActivity){
        Log.i("EditVM", "editEntity: Entro a editar")
        alertDialog = Utils.generateWarningTypeSweetAlertDialog(
            activity,
            "Confirmación",
            "Asegurese de haber colocado correctamente los datos",
            "Enviar",
            "Regresar",
            false,
            true,
        )
        alertDialog.setCancelClickListener { alertDialog.dismissWithAnimation() }
        alertDialog.setConfirmClickListener {
            alertDialog.dismissWithAnimation()
            if(misEdit){
                doEditEntity(activity)
            } else {
                doCreateEntity(activity)

            }
        }
        alertDialog.show()
    }


// InnerClasses & interfaces
    sealed class UserEditNavigation {
        data class ShowError(val error: Throwable) : UserEditNavigation()
        object CloseActivity : UserEditNavigation()

    }

}