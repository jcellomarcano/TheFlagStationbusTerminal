package com.example.thefalgbusstop.presentation.fragments.Hours

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thefalgbusstop.R
import com.example.thefalgbusstop.utils.Event
import com.example.thefalgbusstop.utils.getViewModel
import com.example.thefalgbusstop.utils.setItemDecorationSpacing
import com.example.thefalgbusstop.utils.showLongToast
import com.example.thefalgbusstop.data.*
import com.example.thefalgbusstop.data.network.ApiConstants
import com.example.thefalgbusstop.data.network.HorarioRequest
import com.example.thefalgbusstop.data.repositories.HorariosRepository
import com.example.thefalgbusstop.databinding.HoursFragmentBinding
import com.example.thefalgbusstop.domain.GetAllHorariosUseCase
import com.example.thefalgbusstop.domain.entities.Horarios
import com.example.thefalgbusstop.presentation.Adapters.RecyclerHoursAdapter
import com.example.thefalgbusstop.presentation.fragments.Buses.AgencyDataSource
import kotlinx.android.synthetic.main.buses_fragment.*
import kotlinx.android.synthetic.main.hours_fragment.*


class HoursFragment : Fragment() {
    private lateinit var hoursAdapter: RecyclerHoursAdapter
    private lateinit var listener: OnHoursFragmentListener
    private lateinit var viewModel: HoursViewModel

    companion object {
        fun newInstance(args: Bundle? = Bundle()) = HoursFragment().apply {
            arguments = args
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<HoursFragmentBinding>(
            inflater,
            R.layout.hours_fragment,
            container,
            false
        ).apply {
            lifecycleOwner = this@HoursFragment
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HoursViewModel::class.java)
    }

    interface OnHoursFragmentListener {
        fun openHoursDetail(Hours: Horarios)
    }



    // principal fun for data sources
    private val localAgencyDataSource: LocalAgencyDataSource by lazy {
        AgencyDataSource(AgencyDatabase.getDatabase(requireActivity().applicationContext))
    }

    private val hoursRequest: HorarioRequest by lazy {
        HorarioRequest(ApiConstants.BASE_API_URL)
    }

    private val remoteHoursDataSource: RemoteHorariosDataSource by lazy {
        HorariosRetrofitDataSource(hoursRequest)
    }

    private val hoursRepository: HorariosRepository by lazy {
        HorariosRepository(remoteHoursDataSource, localAgencyDataSource)
    }

    private val getAllHoursUseCase: GetAllHorariosUseCase by lazy {
        GetAllHorariosUseCase(hoursRepository)
    }

    private val hoursViewModel: HoursViewModel by lazy {
        getViewModel { HoursViewModel(getAllHoursUseCase) }
    }

    private val onScrollListener: RecyclerView.OnScrollListener by lazy {
        object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                hoursViewModel.onLoadMoreItems(visibleItemCount, firstVisibleItemPosition, totalItemCount)
                Log.i("TAG", "onScrolled: ${hoursViewModel}")
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            listener = context as OnHoursFragmentListener
        }catch (e: ClassCastException){
            throw ClassCastException("$context must implement OnHoursListFragmentListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hoursAdapter = RecyclerHoursAdapter { Hours ->
            listener.openHoursDetail(Hours)
        }
        hoursAdapter.setHasStableIds(true)

        rvHours.run {
            addOnScrollListener(onScrollListener)
            setItemDecorationSpacing(resources.getDimension(R.dimen.list_item_padding))
            adapter = hoursAdapter
        }

        srwHours.setOnRefreshListener {
            hoursViewModel.onRetryGetAllHours(rvHours.adapter?.itemCount ?: 0)
        }

        hoursViewModel.events.observe(viewLifecycleOwner, Observer(this::validateEvents))

        hoursViewModel.onGetAllHours()
    }
    private fun validateEvents(event: Event<HoursViewModel.HoursListNavigation>) {
        event.getContentIfNotHandled()?.let { navigation ->
            when (navigation) {
                is HoursViewModel.HoursListNavigation.ShowHoursError -> navigation.run {
                    context?.showLongToast("Error -> ${error.message}")
                    Log.i("TAG", "validateEvents: ${error.message}")
                }
                is HoursViewModel.HoursListNavigation.ShowHoursList -> navigation.run {
                    hoursAdapter.addData(HoursList)
                }
                HoursViewModel.HoursListNavigation.HideLoading -> {
                    srwHours.isRefreshing = false
                }
                HoursViewModel.HoursListNavigation.ShowLoading -> {
                    srwHours.isRefreshing = true
                }
//                ShowEmptyListMessage -> TODO()
            }
        }
    }

}