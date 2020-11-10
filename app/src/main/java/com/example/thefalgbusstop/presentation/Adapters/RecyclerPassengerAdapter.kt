package com.example.thefalgbusstop.presentation.Adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thefalgbusstop.R
import com.example.thefalgbusstop.Utils.bindingInflate
import com.example.thefalgbusstop.databinding.PassengerItemBinding
import com.example.thefalgbusstop.domain.Passenger

class RecyclerPassengerAdapter(
    private val listener: (Passenger) -> Unit
): RecyclerView.Adapter<RecyclerPassengerAdapter.PassengerListViewHolder>() {

    private val passengerList: MutableList<Passenger> = mutableListOf()

    fun updateData(newData: List<Passenger>) {
        passengerList.clear()
        passengerList.addAll(newData)
        notifyDataSetChanged()
    }

    fun addData(newData: List<Passenger>) {
        passengerList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ) = PassengerListViewHolder (
        parent.bindingInflate(R.layout.passenger_item, false),
        listener
    )

    override fun getItemId(position: Int): Long = passengerList[position].id.toLong()

    override fun getItemCount()= passengerList.size

    override fun onBindViewHolder(
        holder: PassengerListViewHolder,
        position: Int,
    ) {
        holder.bind(passengerList[position])
    }

    class PassengerListViewHolder(
        private val dataBinding: PassengerItemBinding,
        private val listener: (Passenger) -> Unit
    ): RecyclerView.ViewHolder(dataBinding.root){
        //region Public Methods

        fun bind(item: Passenger){
            dataBinding.passenger = item
            itemView.setOnClickListener { listener(item) }
        }
        //endregion
    }
}
