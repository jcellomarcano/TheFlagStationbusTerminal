package com.example.thefalgbusstop.presentation.Activities

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.thefalgbusstop.utils.Event
import com.example.thefalgbusstop.utils.Utils
import com.example.thefalgbusstop.domain.ChofersUseCase
import android.content.Intent
import com.example.thefalgbusstop.domain.entities.Chofer
import com.example.thefalgbusstop.domain.entities.ChoferPost
import com.example.thefalgbusstop.domain.entities.Passenger
import com.example.thefalgbusstop.domain.entities.responsePojo
import com.example.thefalgbusstop.presentation.Activities.EditItemActivityViewModel.UserEditNavigation.*
import io.reactivex.disposables.CompositeDisposable

class EditItemActivityViewModel(
    private val chofer: Chofer? = null,
    private val chofersUseCase: ChofersUseCase? = null,
    private val passenger: Passenger? = null,

    ): ViewModel() {

    private val disposable = CompositeDisposable()
    private lateinit var alertDialog: SweetAlertDialog


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

    fun showWarningDialog(activity: EditItemActivity){
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
            doEditToServer(activity)

        }
        alertDialog.show()

    }
    // end PubMeth

    fun doEditToServer(activity: EditItemActivity){
        //Dialog View
        val alertDialogLoading = Utils.progressDialog(
            activity,
            "Enviando"
        )
        val newChofer : ChoferPost
        if (chofer != null) {
            newChofer = ChoferPost(
                chofer.name,
                chofer.lastname,
                chofer.rut
            )
            if (chofersUseCase != null) {
                disposable.add(
                    chofersUseCase
                        .invokeCreate(newChofer)
                        .doOnSubscribe { alertDialogLoading.show() }
                        .subscribe({ response ->
                            if (response != null) {
                                alertDialogLoading.hide()
                                val confirmAlertDialog = Utils.confirmDialof(
                                    activity,
                                    "Acción Exitosa",
                                    response.message,
                                    "Continuar"
                                )
                                confirmAlertDialog.setConfirmClickListener {
                                    confirmAlertDialog.dismissWithAnimation()
                                    val intent = Intent(activity, MainActivity::class.java).apply {
                                    }
                                    activity.startActivity(intent)
                                    activity.finish()

                                }
                                confirmAlertDialog.show()
                            } else {
                                alertDialogLoading.hide()
                                val errorDialog = Utils.ErrorAlertDialog(
                                    activity,
                                    "Error en el proceso",
                                    "Disculpe, tuvimos un error interno",
                                    "continuar",
                                    false,
                                    show_cancel_button = false,
                                )
                                errorDialog.setConfirmClickListener {
                                    errorDialog.dismissWithAnimation()
                                    val intent = Intent(activity, MainActivity::class.java).apply {
                                    }
                                    activity.startActivity(intent)
                                    activity.finish()
                                }
                                errorDialog.show()
                            }
                        }, { error ->
                            Log.e("editVM", "doEditToServer: a $error")
                            Log.i("editVM", "doEditToServer: a ${error.localizedMessage}")
                            Log.e("editVM", "doEditToServer: a ${error.printStackTrace()}")
                            Log.i("editVM", "doEditToServer: a ${error.suppressed}")
                            Log.i("editVM", "doEditToServer: a ${error.cause}")

                            alertDialogLoading.hide()
                            val errorDialog = Utils.ErrorAlertDialog(
                                activity,
                                "Error en el proceso",
                                error.toString(),
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
                        }
                        )
                )
            }
        }
    }


    private fun showConfirm(activity: EditItemActivity, response: responsePojo){

    }



    sealed class UserEditNavigation {
        data class ShowError(val error: Throwable) : UserEditNavigation()
        object CloseActivity : UserEditNavigation()

    }

}