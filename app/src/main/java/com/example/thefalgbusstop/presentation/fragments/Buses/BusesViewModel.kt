package com.example.thefalgbusstop.presentation.fragments.Buses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.thefalgbusstop.utils.Event
import com.example.thefalgbusstop.domain.entities.Bus
import com.example.thefalgbusstop.domain.GetAllBusUseCase
import com.example.thefalgbusstop.presentation.fragments.Buses.BusesViewModel.BusListNavigation.*
import io.reactivex.disposables.CompositeDisposable

class BusesViewModel(
    private val getAllBusUseCase: GetAllBusUseCase) : ViewModel(){


    //region Fields
    private val disposable = CompositeDisposable()

    private val _events = MutableLiveData<Event<BusListNavigation>>()
    val events: LiveData<Event<BusListNavigation>> get() = _events

//    val BusBusList: LiveData<List<Bus>>
//        get() = LiveDataReactiveStreams.fromPublisher(getAllBusUseCase.invoke())

    private var currentPage = 1
    private var isLastPage = false
    private var isLoading = false

    //endregion

    //region Public Methods

    fun onLoadMoreItems(visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int) {
        if (isLoading || isLastPage || !isInFooter(visibleItemCount, firstVisibleItemPosition, totalItemCount)) {
            return
        }

        currentPage += 1
        onGetAllBus()
    }

    fun onRetryGetAllBus(itemCount: Int) {
        if (itemCount > 0) {
            _events.value = Event(HideLoading)
            return
        }

        onGetAllBus()
    }

    fun onGetAllBus(){
        disposable.add(
            getAllBusUseCase
                .invoke()
                .doOnSubscribe { showLoading() }
                .subscribe({ BusList ->
                if (BusList.size < PAGE_SIZE) {
                    isLastPage = true
                }
                    hideLoading()
                    _events.value = Event(ShowBusList(BusList))
                }, { error ->
                isLastPage = true
                    hideLoading()
                    _events.value = Event(ShowBusError(error))
                })
        )
    }

    //endregion

    //region Private Methods

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

    //endregion



    //region Inner Classes & Interfaces
    sealed class BusListNavigation {
        data class ShowBusError(val error: Throwable) : BusListNavigation()
        data class ShowBusList(val BusList: List<Bus>) : BusListNavigation()
        //        object ShowEmptyListMessage : BusListNavigation()
        object HideLoading : BusListNavigation()
        object ShowLoading : BusListNavigation()
    }

    //endregion

    //region Companion Object

    companion object {
        private const val PAGE_SIZE = 20
    }

    //endregion

}

