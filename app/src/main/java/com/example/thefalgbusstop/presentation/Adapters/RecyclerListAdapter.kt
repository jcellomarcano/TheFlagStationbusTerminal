package com.example.thefalgbusstop.presentation.Adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thefalgbusstop.R
import com.example.thefalgbusstop.utils.bindImageUrl
import com.example.thefalgbusstop.utils.bindingInflate
import com.example.thefalgbusstop.databinding.ChoferItemFrameBinding
import com.example.thefalgbusstop.domain.entities.Chofer
import kotlinx.android.synthetic.main.chofer_item_frame.view.*

class RecyclerListAdapter(
        private val listener: (Chofer) -> Unit
): RecyclerView.Adapter<RecyclerListAdapter.ChoferListViewHolder>() {

    private val choferList: MutableList<Chofer> = mutableListOf()

    fun updateData(newData: List<Chofer>) {
        choferList.clear()
        choferList.addAll(newData)
        notifyDataSetChanged()
    }

    fun addData(newData: List<Chofer>) {
        choferList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ChoferListViewHolder(
                    parent.bindingInflate(R.layout.chofer_item_frame, false),
                    listener
            )

    override fun getItemCount() = choferList.size

    override fun getItemId(position: Int): Long = choferList[position].id.toLong()

    override fun onBindViewHolder(holder: ChoferListViewHolder, position: Int) {
        holder.bind(choferList[position])
    }

    class ChoferListViewHolder(
            private val dataBinding: ChoferItemFrameBinding,
            private val listener: (Chofer) -> Unit
    ): RecyclerView.ViewHolder(dataBinding.root) {

        //region Public Methods

        fun bind(item: Chofer){
            dataBinding.chofer = item
            itemView.profilePic.bindImageUrl(
                    url = item.imgPerfil,
                    placeholder = R.drawable.ic_camera_alt_black,
                    errorPlaceholder = R.drawable.ic_broken_image_black
            )
            itemView.setOnClickListener { listener(item) }
        }

        //endregion
    }

}