package com.example.thefalgbusstop.presentation.Adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thefalgbusstop.R
import com.example.thefalgbusstop.utils.bindingInflate
import com.example.thefalgbusstop.databinding.HourItemBinding
import com.example.thefalgbusstop.domain.entities.Horarios

class RecyclerHoursAdapter(
    private val listener: (Horarios) -> Unit
): RecyclerView.Adapter<RecyclerHoursAdapter.HoursListViewHolder>() {

    private val hoursList: MutableList<Horarios> = mutableListOf()

    fun updateData(newData: List<Horarios>) {
        hoursList.clear()
        hoursList.addAll(newData)
        notifyDataSetChanged()
    }

    fun addData(newData: List<Horarios>) {
        hoursList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ) = HoursListViewHolder(
        parent.bindingInflate(layoutRes = R.layout.hour_item, attachToRoot = false),
        listener
    )

    override fun getItemId(position: Int): Long = hoursList[position].id.toLong()

    override fun getItemCount() = hoursList.size

    override fun onBindViewHolder(holder: HoursListViewHolder, position: Int) {
        holder.bind(hoursList[position])
    }

    class HoursListViewHolder(
        private val dataBinding: HourItemBinding,
        private val listener: (Horarios) -> Unit
    ): RecyclerView.ViewHolder(dataBinding.root){
        //region Public Methods

        fun bind(item: Horarios){
            dataBinding.hours = item
            itemView.setOnClickListener { listener(item) }
        }

        //endregion
    }


}
