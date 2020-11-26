package com.example.thefalgbusstop.presentation.activities

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.thefalgbusstop.R
import com.example.thefalgbusstop.domain.ChofersUseCase
import com.example.thefalgbusstop.utils.Event
import com.example.thefalgbusstop.domain.entities.Chofer
import com.example.thefalgbusstop.domain.entities.Passenger
import com.example.thefalgbusstop.parcelables.toChoferParcelable
import com.example.thefalgbusstop.presentation.activities.ItemDetailActivityViewModel.UserDetailNavigation.CloseActivity
import com.example.thefalgbusstop.utils.Cosntants
import com.example.thefalgbusstop.utils.Utils
import com.example.thefalgbusstop.utils.startActivity
import io.reactivex.disposables.CompositeDisposable


class ItemDetailActivityViewModel(
    private val passenger: Passenger? = null,
    private val chofer: Chofer? = null,
    private val chofersUseCase: ChofersUseCase?
): ViewModel(){

    private val disposable = CompositeDisposable()
    private lateinit var alertDialog: SweetAlertDialog

    private  val _choferValues = MutableLiveData<Chofer>()
    val choferValues: LiveData<Chofer> get() = _choferValues

    private val _passengerValues = MutableLiveData<Passenger>()
    val passengerValues: LiveData<Passenger> get() = _passengerValues

    private val _events = MutableLiveData<Event<UserDetailNavigation>>()
    val events: LiveData<Event<UserDetailNavigation>> get() = _events

    //override
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
    // end override

    //Pub Meths
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


    fun showWarningDialog(activity: ItemDetailActivity){
        Log.i("EditVM", "editEntity: Entro a editar")
        alertDialog = Utils.generateWarningTypeSweetAlertDialog(
            activity,
            "Borrar Elemento",
            "Esta seguro que desea eliminar este chofer?",
            "Borrar",
            "Regresar",
            false,
            true,
        )
        alertDialog.setCancelClickListener { alertDialog.dismissWithAnimation() }
        alertDialog.setConfirmClickListener {
            alertDialog.dismissWithAnimation()
            deleteEntity(activity)

        }
        alertDialog.show()

    }



    fun openEditChofer(activity: Activity) {
        activity.startActivity<EditItemActivity> {
            putExtra(Cosntants.EXTRA_CHOFER, chofer?.toChoferParcelable())
            putExtra("isEdit", true)
        }
        activity.overridePendingTransition(R.anim.entry, R.anim.exit)
    }
    //end PubMeth

    //privateMeth
    private fun successDelete(activity: ItemDetailActivity, message: String){
        val confirmAlertDialog = Utils.confirmDialof(
            activity,
            "Eliminado exitosamente",
            message,
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
    }

    private fun errorDelete(activity: ItemDetailActivity, error: String){
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
    }

    private fun deleteEntity(activity: ItemDetailActivity) {
        //Loading
        val alertDialogLoading = Utils.progressDialog(
            activity,
            "Eliminando"
        )
        if(this.chofersUseCase != null) {
            disposable.add(
                chofersUseCase.invokeDelete(chofer!!.id)
                    .doOnSubscribe { alertDialogLoading.show() }
                    .subscribe({
                            response ->
                        if (response != null){
                            alertDialogLoading.hide()
                            successDelete(activity, response.message)
                        }
                    },{ error ->
                        alertDialogLoading.hide()
                        errorDelete(activity, error.message.toString())
                    })
            )
        }

    }


    //end PrivateMeth

    //region Inner Classes & Interfaces

    sealed class UserDetailNavigation {
        data class ShowError(val error: Throwable) : UserDetailNavigation()
        object CloseActivity : UserDetailNavigation()

    }

    //endregion
}