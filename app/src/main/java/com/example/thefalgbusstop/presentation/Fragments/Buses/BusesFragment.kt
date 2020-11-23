package com.example.thefalgbusstop.presentation.Fragments.Buses

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thefalgbusstop.R
import com.example.thefalgbusstop.utils.getViewModel
import com.example.thefalgbusstop.data.AgencyDatabase
import com.example.thefalgbusstop.data.BusRetrofitDataSource
import com.example.thefalgbusstop.data.RemoteBusDataSource
import com.example.thefalgbusstop.data.network.ApiConstants
import com.example.thefalgbusstop.data.network.BusRequest
import com.example.thefalgbusstop.data.repositories.BusRepository
import com.example.thefalgbusstop.databinding.BusesFragmentBinding
import com.example.thefalgbusstop.domain.entities.Bus
import com.example.thefalgbusstop.domain.GetAllBusUseCase
import com.example.thefalgbusstop.presentation.Adapters.RecyclerBusAdapter
import com.example.thefalgbusstop.utils.Event
import com.example.thefalgbusstop.utils.setItemDecorationSpacing
import com.example.thefalgbusstop.utils.showLongToast
import com.example.thefalgbusstop.data.LocalAgencyDataSource
import kotlinx.android.synthetic.main.buses_fragment.*


class BusesFragment : Fragment() {

    private lateinit var busAdapter: RecyclerBusAdapter
    private lateinit var listener: OnBusFragmentListener

//region Companion object

    companion object {

        fun newInstance(args: Bundle? = Bundle()) = BusesFragment().apply {
            arguments = args
        }
    }


    private lateinit var viewModel: BusesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<BusesFragmentBinding>(
            inflater,
            R.layout.buses_fragment,
            container,
            false
        ).apply {
            lifecycleOwner = this@BusesFragment
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BusesViewModel::class.java)
    }

    //region Inner Classes & Interfaces
    interface OnBusFragmentListener {
        fun openBusDetail(Bus: Bus)
    }


    // principal fun for data sources
    private val localAgencyDataSource: LocalAgencyDataSource by lazy {
        AgencyDataSource(AgencyDatabase.getDatabase(requireActivity().applicationContext))
    }

    private val busRequest: BusRequest by lazy {
        BusRequest(ApiConstants.BASE_API_URL)
    }

    private val remoteBusDataSource: RemoteBusDataSource by lazy {
        BusRetrofitDataSource(busRequest)
    }

    private val busRepository: BusRepository by lazy {
        BusRepository(remoteBusDataSource, localAgencyDataSource)
    }

    private val getAllBusUseCase: GetAllBusUseCase by lazy {
        GetAllBusUseCase(busRepository)
    }

    private val busViewModel: BusesViewModel by lazy {
        getViewModel { BusesViewModel(getAllBusUseCase)}
    }

    private val onScrollListener: RecyclerView.OnScrollListener by lazy {
        object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                busViewModel.onLoadMoreItems(visibleItemCount, firstVisibleItemPosition, totalItemCount)
                Log.i("TAG", "onScrolled: ${busViewModel}")
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            listener = context as OnBusFragmentListener
        }catch (e: ClassCastException){
            throw ClassCastException("$context must implement OnBusListFragmentListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        busAdapter = RecyclerBusAdapter { Bus ->
            listener.openBusDetail(Bus)
        }
        busAdapter.setHasStableIds(true)

        rvBus.run {
            addOnScrollListener(onScrollListener)
            setItemDecorationSpacing(resources.getDimension(R.dimen.list_item_padding))
            adapter = busAdapter
        }

        srwBus.setOnRefreshListener {
            busViewModel.onRetryGetAllBus(rvBus.adapter?.itemCount ?: 0)
        }

//        BusList.observe(this, Observer(BusListViewModel::onBusList))
        busViewModel.events.observe(viewLifecycleOwner, Observer(this::validateEvents))

        busViewModel.onGetAllBus()
    }

    private fun validateEvents(event: Event<BusesViewModel.BusListNavigation>) {
        event?.getContentIfNotHandled()?.let { navigation ->
            when (navigation) {
                is BusesViewModel.BusListNavigation.ShowBusError -> navigation.run {
                    context?.showLongToast("Error -> ${error.message}")
                    Log.i("TAG", "validateEvents: ${error.message}")
                }
                is BusesViewModel.BusListNavigation.ShowBusList -> navigation.run {
                    busAdapter.addData(BusList)
                }
                BusesViewModel.BusListNavigation.HideLoading -> {
                    srwBus.isRefreshing = false
                }
                BusesViewModel.BusListNavigation.ShowLoading -> {
                    srwBus.isRefreshing = true
                }
//                ShowEmptyListMessage -> TODO()
            }
        }
    }



}


