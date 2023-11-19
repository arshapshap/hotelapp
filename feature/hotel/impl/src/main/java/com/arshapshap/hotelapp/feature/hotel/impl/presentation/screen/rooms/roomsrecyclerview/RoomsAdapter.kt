package com.arshapshap.hotelapp.feature.hotel.impl.presentation.screen.rooms.roomsrecyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arshapshap.hotelapp.feature.hotel.impl.R
import com.arshapshap.hotelapp.feature.hotel.impl.databinding.ItemRoomInfoBinding
import com.arshapshap.hotelapp.feature.hotel.impl.domain.Room
import com.arshapshap.hotelapp.feature.hotel.impl.presentation.common.imagecarousel.ImageCarouselAdapter
import com.arshapshap.hotelapp.feature.hotel.impl.presentation.common.imagecarousel.ImageCarouselLoader
import com.arshapshap.hotelapp.feature.hotel.impl.presentation.common.peculiaritiesrecyclerview.PeculiaritiesAdapter
import com.arshapshap.hotelapp.feature.hotel.impl.presentation.common.utils.formatToPrice


internal class RoomsAdapter(
    private var list: List<Room> = listOf(),
    private val onSelectRoom: (Int) -> Unit,
    private val onOpenDetails: (Int) -> Unit
) : RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Room>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(getBinding(parent).apply {
            layoutImagesCarousel.viewPagerImages.adapter = ImageCarouselAdapter()
            recyclerViewPeculiarities.root.adapter = PeculiaritiesAdapter()
        })

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.binding.run {
        (list[position]).let { room ->
            ImageCarouselLoader(
                context = root.context,
                adapter = layoutImagesCarousel.viewPagerImages.adapter as ImageCarouselAdapter
            ).loadImages(room.imageUrls)

            textViewRoomName.text = room.name
            textViewPrice.text = root.context.resources.getString(R.string.room_price, room.price.formatToPrice())
            textViewPriceDescription.text = room.pricePer
            (recyclerViewPeculiarities.root.adapter as PeculiaritiesAdapter).setList(room.peculiarities)

            buttonSelectRoom.setOnClickListener {
                onSelectRoom.invoke(room.id)
            }
            tagViewDetails.setOnClickListener {
                onOpenDetails.invoke(room.id)
            }
        }
    }

    private fun getBinding(parent: ViewGroup): ItemRoomInfoBinding =
        ItemRoomInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    class ViewHolder(val binding: ItemRoomInfoBinding) : RecyclerView.ViewHolder(binding.root)
}
