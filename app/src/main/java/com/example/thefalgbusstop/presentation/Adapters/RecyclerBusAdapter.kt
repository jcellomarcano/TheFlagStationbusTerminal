package com.example.thefalgbusstop.presentation.Adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thefalgbusstop.R
import com.example.thefalgbusstop.Utils.bindImageUrl
import com.example.thefalgbusstop.Utils.bindingInflate
import com.example.thefalgbusstop.databinding.BusesItemBinding
import com.example.thefalgbusstop.domain.Bus
import kotlinx.android.synthetic.main.buses_item.view.*


class RecyclerBusAdapter ( private val listener: (Bus) -> Unit):
    RecyclerView.Adapter<RecyclerBusAdapter.BusesListViewHolder>()  {
    private val busList: MutableList<Bus> = mutableListOf()

    fun updateData(newData: List<Bus>) {
        busList.clear()
        busList.addAll(newData)
        notifyDataSetChanged()
    }

    fun addData(newData: List<Bus>) {
        busList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BusesListViewHolder(
            parent.bindingInflate(R.layout.buses_item, false),
            listener
        )

    override fun getItemCount() = busList.size

    override fun getItemId(position: Int): Long = busList[position].id.toLong()

    override fun onBindViewHolder(holder: BusesListViewHolder, position: Int) {
        holder.bind(busList[position])
    }

    class BusesListViewHolder(
        private val dataBinding: BusesItemBinding,
        private val listener: (Bus) -> Unit
    ): RecyclerView.ViewHolder(dataBinding.root) {

        //region Public Methods

        fun bind(item: Bus){
            dataBinding.bus = item
            itemView.busPic.bindImageUrl(
                url = item.busImg,
                placeholder = R.drawable.ic_camera_alt_black,
                errorPlaceholder = R.drawable.ic_broken_image_black
            )
            itemView.nombreChofer.text = item.choferId.toString()
            itemView.setOnClickListener { listener(item) }
        }

        //endregion
    }
}