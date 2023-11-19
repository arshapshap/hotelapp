package com.arshapshap.hotelapp.feature.hotel.impl.presentation.common.peculiaritiesrecyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arshapshap.hotelapp.designsystem.TagView
import com.arshapshap.hotelapp.feature.hotel.impl.databinding.ItemPeculiarityBinding


internal class PeculiaritiesAdapter(
    private var list: List<String> = listOf(),
) : RecyclerView.Adapter<PeculiaritiesAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(getBinding(parent).root)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.view.run {
        text = list[position]
    }

    private fun getBinding(parent: ViewGroup): ItemPeculiarityBinding =
        ItemPeculiarityBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    class ViewHolder(val view: TagView) : RecyclerView.ViewHolder(view)
}
