package com.arshapshap.hotelapp.feature.hotel.presentation.common.imagecarousel

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arshapshap.hotelapp.feature.hotel.databinding.ItemCarouselImageBinding

internal class ImageCarouselAdapter(
    private var list: List<Drawable?> = listOf(),
) : RecyclerView.Adapter<ImageCarouselAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Drawable?>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setItem(drawable: Drawable?, position: Int) {
        this.list = list.toMutableList().also {
            it[position] = drawable
        }.toList()
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(getBinding(parent))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.binding.run {
        imageView.setImageDrawable(list[position])
    }

    private fun getBinding(parent: ViewGroup): ItemCarouselImageBinding =
        ItemCarouselImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    class ViewHolder(val binding: ItemCarouselImageBinding) : RecyclerView.ViewHolder(binding.root)
}