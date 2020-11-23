package com.example.thefalgbusstop.presentation.Activities

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.thefalgbusstop.R
import com.example.thefalgbusstop.utils.Cosntants
import com.example.thefalgbusstop.utils.Event
import com.example.thefalgbusstop.utils.startActivity
import com.example.thefalgbusstop.domain.entities.Chofer
import com.example.thefalgbusstop.domain.entities.ChoferPost
import com.example.thefalgbusstop.domain.entities.Passenger
import com.example.thefalgbusstop.parcelables.toChoferParcelable
import com.example.thefalgbusstop.presentation.Activities.ItemDetailActivityViewModel.UserDetailNavigation.CloseActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.disposables.CompositeDisposable


class ItemDetailActivityViewModel(
    private val passenger: Passenger? = null,
    private val chofer: Chofer? = null,
): ViewModel(){

    private val disposable = CompositeDisposable()

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

    fun addChofer(activity: ItemDetailActivityViewModel) {

    }



    fun deleteChofer(activity: ItemDetailActivity) {
        SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("Are you sure?")
            .setContentText("Won't be able to recover this file!")
            .setConfirmText("Yes,delete it!")
            .setConfirmClickListener { sDialog ->
                sDialog.dismissWithAnimation()
                if (chofer != null) {
//                    activity.choferRepository.deleteChofer(chofer.id)
                    val gson = Gson()
                    val gsonPretty = GsonBuilder().setPrettyPrinting().create()
                    val newChofer = ChoferPost(
                        "Manolo",
                        "Suarez",
                        "1928374-6"
                    )
                    val jsonNewChofer: String = gson.toJson(newChofer)
                    Log.i("ViewItemModel", "deleteChofer: $jsonNewChofer")
                    val jsonNewChoferPretty: String = gsonPretty.toJson(newChofer)
                    Log.i("ViewItemModel", "deleteChofer: $jsonNewChoferPretty")

                    activity.choferRepository.createChofer(newChofer)
                }
            }
            .show()
    }

    fun openEditChofer(activity: Activity) {
        activity.startActivity<EditItemActivity> {
            putExtra(Cosntants.EXTRA_CHOFER, chofer?.toChoferParcelable())
        }
        activity.overridePendingTransition(R.anim.entry, R.anim.exit)
    }
    //end PubMeth


    //privateMeth



    //end PrivateMeth







    //region Inner Classes & Interfaces

    sealed class UserDetailNavigation {
        data class ShowEpisodeError(val error: Throwable) : UserDetailNavigation()
        object CloseActivity : UserDetailNavigation()
//        object HideEpisodeListLoading : UserDetailNavigation()
//        object ShowEpisodeListLoading : UserDetailNavigation()
    }

    //endregion
}