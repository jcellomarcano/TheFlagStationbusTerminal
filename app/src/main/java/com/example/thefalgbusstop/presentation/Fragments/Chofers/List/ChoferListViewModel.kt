package com.example.thefalgbusstop.presentation.Fragments.Chofers.List

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thefalgbusstop.utils.Event
import com.example.thefalgbusstop.presentation.Fragments.Chofers.List.ChoferListViewModel.ChoferListNavigation.*
import com.example.thefalgbusstop.domain.entities.Chofer
import com.example.thefalgbusstop.domain.ChofersUseCase
import io.reactivex.disposables.CompositeDisposable

class ChoferListViewModel(
        private val chofersUseCase: ChofersUseCase) : ViewModel() {

    //region Fields

    private val disposable = CompositeDisposable()

    private val _events = MutableLiveData<Event<ChoferListNavigation>>()
    val events: LiveData<Event<ChoferListNavigation>> get() = _events

//    val choferChoferList: LiveData<List<Chofer>>
//        get() = LiveDataReactiveStreams.fromPublisher(getAllChofersUseCase.invoke())

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
        onGetAllChofers()
    }

    fun onRetryGetAllChofer(itemCount: Int) {
        if (itemCount > 0) {
            _events.value = Event(HideLoading)
            return
        }

        onGetAllChofers()
    }

    fun onGetAllChofers(){
        disposable.add(
                chofersUseCase
                        .invoke()
                        .doOnSubscribe { showLoading() }
                        .subscribe({ choferList ->
                            if (choferList.size < PAGE_SIZE) {
                                isLastPage = true
                            }
                            hideLoading()
                            _events.value = Event(ShowChoferList(choferList))
                        }, { error ->
                            isLastPage = true
                            hideLoading()
                            _events.value = Event(ShowChoferError(error))
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
    sealed class ChoferListNavigation {
        data class ShowChoferError(val error: Throwable) : ChoferListNavigation()
        data class ShowChoferList(val choferList: List<Chofer>) : ChoferListNavigation()
        object HideLoading : ChoferListNavigation()
        object ShowLoading : ChoferListNavigation()
    }

    //endregion

    //region Companion Object

    companion object {

        private const val PAGE_SIZE = 20
    }

    //endregion


}