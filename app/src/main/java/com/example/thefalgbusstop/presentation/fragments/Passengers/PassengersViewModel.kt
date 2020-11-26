package com.example.thefalgbusstop.presentation.fragments.Passengers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thefalgbusstop.utils.Event
import com.example.thefalgbusstop.domain.GetAllPassengersUseCase
import com.example.thefalgbusstop.domain.entities.Passenger
import io.reactivex.disposables.CompositeDisposable
import com.example.thefalgbusstop.presentation.fragments.Passengers.PassengersViewModel.PassengerListNavigation.*

class PassengersViewModel(
    private  val getAllPassengersUseCase: GetAllPassengersUseCase
) : ViewModel() {
    // Fields
    private val disposable = CompositeDisposable()

    private val _events = MutableLiveData<Event<PassengerListNavigation>>()
    val events: LiveData<Event<PassengerListNavigation>> get() = _events

    private var currentPage = 1
    private var isLastPage = false
    private var isLoading = false
    // end Fields

    //Public Meths
    fun onLoadMoreItems(visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int) {
        if (isLoading || isLastPage || !isInFooter(visibleItemCount, firstVisibleItemPosition, totalItemCount)) {
            return
        }

        currentPage += 1
        onGetAllPassengers()
    }

    fun onRetryGetAllPassengers(itemCount: Int) {
        if (itemCount > 0) {
            _events.value = Event(HideLoading)
            return
        }

        onGetAllPassengers()
    }

    fun onGetAllPassengers(){
        disposable.add(
            getAllPassengersUseCase
                .invoke()
                .doOnSubscribe { showLoading() }
                .subscribe({ passengerList ->
                    if (passengerList.size < PAGE_SIZE) {
                        isLastPage = true
                    }

                    hideLoading()
                    _events.value = Event(ShowPassengerList(
                        passengerList))
                }, { error ->
                    isLastPage = true
                    hideLoading()
                    _events.value = Event(ShowPassengerError(
                        error))
                })
        )
    }



    //end Pub Meths

    // Privt Meth
    private fun isInFooter(
        visibleItemCount: Int,
        firstVisibleItemPosition: Int,
        totalItemCount: Int
    ): Boolean {
        return visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE
    }

    private fun showLoading() {
        isLoading = true
        _events.value = Event(ShowLoading)
    }

    private fun hideLoading() {
        isLoading = false
        _events.value = Event(HideLoading)
    }

    // end Priv Meth

    //region Inner Classes & Interfaces
    sealed class PassengerListNavigation {
        data class ShowPassengerError(val error: Throwable) : PassengerListNavigation()
        data class ShowPassengerList(val passengerList: List<Passenger>) : PassengerListNavigation()
        object HideLoading : PassengerListNavigation()
        object ShowLoading : PassengerListNavigation()
    }

    //endregion

    //region Companion Object

    companion object {

        private const val PAGE_SIZE = 20
    }

    //endregion
}