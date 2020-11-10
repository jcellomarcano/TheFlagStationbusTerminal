package com.example.thefalgbusstop.presentation.Fragments.Hours

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thefalgbusstop.Utils.Event
import com.example.thefalgbusstop.domain.GetAllHorariosUseCase
import com.example.thefalgbusstop.domain.Horarios
import io.reactivex.disposables.CompositeDisposable

class HoursViewModel(
    private val getAllHorariosUseCase: GetAllHorariosUseCase
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _events = MutableLiveData<Event<HoursListNavigation>>()
    val events: LiveData<Event<HoursListNavigation>> get() = _events

    private var currentPage = 1
    private var isLastPage = false
    private var isLoading = false

    fun onLoadMoreItems(visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int) {
        if (isLoading || isLastPage || !isInFooter(visibleItemCount, firstVisibleItemPosition, totalItemCount)) {
            return
        }

        currentPage += 1
        onGetAllHours()
    }

    fun onRetryGetAllHours(itemCount: Int) {
        if (itemCount > 0) {
            _events.value = Event(HoursListNavigation.HideLoading)
            return
        }

        onGetAllHours()
    }

    fun onGetAllHours(){
        disposable.add(
            getAllHorariosUseCase
                .invoke()
                .doOnSubscribe { showLoading() }
                .subscribe({ HoursList ->
                    if (HoursList.size < PAGE_SIZE) {
                        isLastPage = true
                    }

                    hideLoading()
                    _events.value = Event(HoursListNavigation.ShowHoursList(HoursList))
                }, { error ->
                    isLastPage = true
                    hideLoading()
                    _events.value = Event(HoursListNavigation.ShowHoursError(error))
                })
        )
    }

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
        _events.value = Event(HoursListNavigation.ShowLoading)
    }

    private fun hideLoading() {
        isLoading = false
        _events.value = Event(HoursListNavigation.HideLoading)
    }

    //endregion



    //region Inner Classes & Interfaces
    sealed class HoursListNavigation {
        data class ShowHoursError(val error: Throwable) : HoursListNavigation()
        data class ShowHoursList(val HoursList: List<Horarios>) : HoursListNavigation()
        object HideLoading : HoursListNavigation()
        object ShowLoading : HoursListNavigation()
    }

    //endregion

    //region Companion Object

    companion object {
        private const val PAGE_SIZE = 20
    }

    //endregion

}