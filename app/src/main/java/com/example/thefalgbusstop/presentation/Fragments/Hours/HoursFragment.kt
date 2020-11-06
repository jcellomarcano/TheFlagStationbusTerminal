package com.example.thefalgbusstop.presentation.Fragments.Hours

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thefalgbusstop.R

class HoursFragment : Fragment() {

    companion object {
        fun newInstance() = HoursFragment()
    }

    private lateinit var viewModel: HoursViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hours_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HoursViewModel::class.java)
        // TODO: Use the ViewModel
    }

}