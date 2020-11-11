package com.example.thefalgbusstop.presentation.Activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thefalgbusstop.Utils.Event
import com.example.thefalgbusstop.domain.Chofer
import com.example.thefalgbusstop.domain.Passenger
import io.reactivex.disposables.CompositeDisposable
import com.example.thefalgbusstop.presentation.Activities.ItemDetailActivityViewModel.UserDetailNavigation.*

class ItemDetailActivityViewModel (
    private val chofer: Chofer? = null,
    private val passenger: Passenger? = null): ViewModel(){

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
    //end PubMeth


    //privateMeth
    private fun getSit(passengerID: Int){

    }

    private fun getBus(choferID: Int){

    }

    private fun getHour(){

    }
    //end PrivateMeth



    //region Inner Classes & Interfaces

    sealed class UserDetailNavigation {
        data class ShowEpisodeError(val error: Throwable) : UserDetailNavigation()
        object CloseActivity : UserDetailNavigation()
        object HideEpisodeListLoading : UserDetailNavigation()
        object ShowEpisodeListLoading : UserDetailNavigation()
    }

    //endregion
}