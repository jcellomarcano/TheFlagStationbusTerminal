package com.example.thefalgbusstop.presentation.Fragments.Passengers

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
import com.example.thefalgbusstop.data.network.PassengerRequest
import com.example.thefalgbusstop.data.repositories.PassengerRepository
import com.example.thefalgbusstop.databinding.PassengersFragmentBinding
import com.example.thefalgbusstop.domain.GetAllPassengersUseCase
import com.example.thefalgbusstop.domain.entities.Passenger
import com.example.thefalgbusstop.presentation.Adapters.RecyclerPassengerAdapter
import com.example.thefalgbusstop.presentation.Fragments.Buses.AgencyDataSource
import com.example.thefalgbusstop.presentation.Fragments.Passengers.PassengersViewModel.PassengerListNavigation.*
import kotlinx.android.synthetic.main.passengers_fragment.*


class PassengersFragment : Fragment() {

    private lateinit var recyclerPassengerAdapter: RecyclerPassengerAdapter
    private lateinit var listener: OnPassengersFragmentListener
    private lateinit var viewModel: PassengersViewModel


    companion object {
        fun newInstance(args: Bundle? = Bundle()) = PassengersFragment().apply {
            arguments = args
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<PassengersFragmentBinding>(
            inflater,
            R.layout.passengers_fragment,
            container,
            false
        ).apply {
            lifecycleOwner = this@PassengersFragment
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PassengersViewModel::class.java)
        // TODO: Use the ViewModel
    }
    interface OnPassengersFragmentListener {
        fun openPassengerDetail(passenger: Passenger)
    }

    // principal fun for data sources
    private val localAgencyDataSource: LocalAgencyDataSource by lazy {
        AgencyDataSource(AgencyDatabase.getDatabase(requireActivity().applicationContext))
    }

    private val passengerRequest: PassengerRequest by lazy {
        PassengerRequest(ApiConstants.BASE_API_URL)
    }

    private val remotePassengerDataSource: RemotePassengerDataSource by lazy {
        PassengerRetrofitDataSource(passengerRequest)
    }

    private val passengerRepository: PassengerRepository by lazy {
        PassengerRepository(remotePassengerDataSource, localAgencyDataSource)
    }

    private val getAllPassengersUseCase: GetAllPassengersUseCase by lazy {
        GetAllPassengersUseCase(passengerRepository)
    }

    private val passengersViewModel: PassengersViewModel by lazy {
        getViewModel { PassengersViewModel(getAllPassengersUseCase) }
    }

    private val onScrollListener: RecyclerView.OnScrollListener by lazy {
        object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                passengersViewModel.onLoadMoreItems(visibleItemCount, firstVisibleItemPosition, totalItemCount)
                Log.i("TAG", "onScrolled: ${passengersViewModel}")
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            listener = context as OnPassengersFragmentListener
        }catch (e: ClassCastException){
            throw ClassCastException("$context must implement OnPassengersFragmentListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerPassengerAdapter = RecyclerPassengerAdapter { Passenger ->
            listener.openPassengerDetail(Passenger)
        }
        recyclerPassengerAdapter.setHasStableIds(true)

        rvPassengerList.run {
            addOnScrollListener(onScrollListener)
            setItemDecorationSpacing(resources.getDimension(R.dimen.list_item_padding))
            adapter = recyclerPassengerAdapter
        }

        srwPassengerList.setOnRefreshListener {
            passengersViewModel.onRetryGetAllPassengers(rvPassengerList.adapter?.itemCount ?: 0)
        }

        passengersViewModel.events.observe(viewLifecycleOwner, Observer(this::validateEvents))

        passengersViewModel.onGetAllPassengers()
    }

    private fun validateEvents(event: Event<PassengersViewModel.PassengerListNavigation>?) {
        event?.getContentIfNotHandled()?.let { navigation ->
            when (navigation) {
                is ShowPassengerError -> navigation.run {
                    context?.showLongToast("Error -> ${error.message}")
                    Log.i("TAG", "validateEvents: ${error.message}")
                }
                is ShowPassengerList -> navigation.run {
                    recyclerPassengerAdapter.addData(passengerList)
                }
                HideLoading -> {
                    srwPassengerList.isRefreshing = false
                }
                ShowLoading -> {
                    srwPassengerList.isRefreshing = true
                }
//                ShowEmptyListMessage -> TODO()
            }
        }
    }
}