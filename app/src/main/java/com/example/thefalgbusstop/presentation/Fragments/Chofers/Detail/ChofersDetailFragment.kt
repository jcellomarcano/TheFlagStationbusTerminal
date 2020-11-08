package com.example.thefalgbusstop.presentation.Fragments.Chofers.Detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thefalgbusstop.R
import com.example.thefalgbusstop.data.*
import com.example.thefalgbusstop.data.network.ApiConstants
import com.example.thefalgbusstop.data.network.ChoferRequest
import com.example.thefalgbusstop.data.repositories.ChoferRepository

class ChofersDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ChofersDetailFragment()
    }

    private lateinit var viewModel: ChofersDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.chofers_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChofersDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

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

}