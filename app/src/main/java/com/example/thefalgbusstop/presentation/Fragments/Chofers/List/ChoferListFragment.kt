package com.example.thefalgbusstop.presentation.Fragments.Chofers.List

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
import com.example.thefalgbusstop.Utils.Event
import com.example.thefalgbusstop.Utils.getViewModel
import com.example.thefalgbusstop.Utils.setItemDecorationSpacing
import com.example.thefalgbusstop.Utils.showLongToast
import com.example.thefalgbusstop.data.*
import com.example.thefalgbusstop.data.network.ApiConstants
import com.example.thefalgbusstop.data.network.ChoferRequest
import com.example.thefalgbusstop.data.repositories.ChoferRepository
import com.example.thefalgbusstop.databinding.ChofersFragmentBinding
import com.example.thefalgbusstop.domain.Chofer
import com.example.thefalgbusstop.domain.GetAllChofersUseCase
import  com.example.thefalgbusstop.presentation.Fragments.Chofers.List.ChoferListViewModel.ChoferListNavigation.*
import com.example.thefalgbusstop.presentation.Adapters.RecyclerListAdapter
import kotlinx.android.synthetic.main.chofers_fragment.*

class ChoferListFragment : Fragment() {

    private lateinit var recyclerListAdapter: RecyclerListAdapter
    private lateinit var listener: OnChoferListFragmentListener

//region Companion object

    companion object {

        fun newInstance(args: Bundle? = Bundle()) = ChoferListFragment().apply {
            arguments = args
        }
    }

    private lateinit var viewModel: ChoferListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<ChofersFragmentBinding>(
                inflater,
                R.layout.chofers_fragment,
                container,
                false
        ).apply {
            lifecycleOwner = this@ChoferListFragment
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChoferListViewModel::class.java)
    }

    //region Inner Classes & Interfaces
    interface OnChoferListFragmentListener {
        fun openChoferDetail(chofer: Chofer)
    }


    // principal fun for data sources
    private val localChoferDataSource: LocalChoferDataSource by lazy {
        ChoferRoomDataSource(AgencyDatabase.getDatabase(requireActivity().applicationContext))
    }

    private val choferRequest: ChoferRequest by lazy {
        ChoferRequest(ApiConstants.BASE_API_URL)
    }

    private val remoteChoferDataSource: RemoteChoferDataSource by lazy {
        ChoferRetrofitDataSource(choferRequest)
    }

    private val choferRepository: ChoferRepository by lazy {
        ChoferRepository(remoteChoferDataSource, localChoferDataSource)
    }

    private val getAllChofersUseCase: GetAllChofersUseCase by lazy {
        GetAllChofersUseCase(choferRepository)
    }

    private val choferListViewModel: ChoferListViewModel by lazy {
        getViewModel { ChoferListViewModel(getAllChofersUseCase) }
    }

    private val onScrollListener: RecyclerView.OnScrollListener by lazy {
        object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                choferListViewModel.onLoadMoreItems(visibleItemCount, firstVisibleItemPosition, totalItemCount)
                Log.i("TAG", "onScrolled: ${choferListViewModel}")
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            listener = context as OnChoferListFragmentListener
        }catch (e: ClassCastException){
            throw ClassCastException("$context must implement OnChoferListFragmentListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerListAdapter = RecyclerListAdapter { Chofer ->
            listener.openChoferDetail(Chofer)
        }
        recyclerListAdapter.setHasStableIds(true)

        rvChoferList.run {
            addOnScrollListener(onScrollListener)
            setItemDecorationSpacing(resources.getDimension(R.dimen.list_item_padding))
            adapter = recyclerListAdapter
        }

        srwChoferList.setOnRefreshListener {
            choferListViewModel.onRetryGetAllChofer(rvChoferList.adapter?.itemCount ?: 0)
        }

        choferListViewModel.events.observe(viewLifecycleOwner, Observer(this::validateEvents))

        choferListViewModel.onGetAllChofers()
    }

    private fun validateEvents(event: Event<ChoferListViewModel.ChoferListNavigation>?) {
        event?.getContentIfNotHandled()?.let { navigation ->
            when (navigation) {
                is ShowChoferError -> navigation.run {
                    context?.showLongToast("Error -> ${error.message}")
                    Log.i("TAG", "validateEvents: ${error.message}")
                }
                is ShowChoferList -> navigation.run {
                    recyclerListAdapter.addData(choferList)
                }
                HideLoading -> {
                    srwChoferList.isRefreshing = false
                }
                ShowLoading -> {
                    srwChoferList.isRefreshing = true
                }
//                ShowEmptyListMessage -> TODO()
            }
        }
    }
}