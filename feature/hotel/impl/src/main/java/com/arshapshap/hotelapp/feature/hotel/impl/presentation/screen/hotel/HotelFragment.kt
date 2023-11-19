package com.arshapshap.hotelapp.feature.hotel.impl.presentation.screen.hotel

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import coil.imageLoader
import coil.request.ImageRequest
import com.arshapshap.hotelapp.core.presentation.BaseFragment
import com.arshapshap.hotelapp.feature.hotel.impl.presentation.common.imagecarousel.ImageCarouselAdapter
import com.arshapshap.hotelapp.feature.hotel.impl.R
import com.arshapshap.hotelapp.feature.hotel.impl.databinding.FragmentHotelBinding
import com.arshapshap.hotelapp.feature.hotel.impl.presentation.common.tagsrecyclerview.PeculiaritiesAdapter
import com.google.android.flexbox.FlexboxLayoutManager
import java.text.DecimalFormat


internal class HotelFragment : BaseFragment<FragmentHotelBinding, HotelViewModel>(
    FragmentHotelBinding::inflate
) {

    override val viewModel: HotelViewModel by lazy {
        HotelViewModel()
    }

    override fun initViews() {
        with(binding) {
            viewPagerHotelImages.adapter = ImageCarouselAdapter()
            recyclerViewPeculiarities.adapter = PeculiaritiesAdapter()

            recyclerViewPeculiarities.layoutManager = FlexboxLayoutManager(requireContext())
        }
    }

    override fun subscribe() {
        viewModel.loadData()

        viewModel.hotel.observe(viewLifecycleOwner) {
            with(binding) {
                getImageCarouselAdapter().setList(getPlaceholdersList(it.imageUrls.size))

                it.imageUrls.forEachIndexed { index, it ->
                    loadImage(url = it) { drawable ->
                        Log.e("LALALA", drawable.toString())
                        getImageCarouselAdapter().setItem(drawable, index)
                    }
                }

                tagViewRating.text = resources.getString(R.string.rating, it.rating, it.ratingName)
                textViewHotelName.text = it.name
                textViewLocation.text = it.adress
                textViewPrice.text = resources.getString(R.string.minimal_price, it.minimalPrice.format())
                textViewPriceDescription.text = it.priceForIt
                textViewAboutHotel.text = it.aboutTheHotel.description
                getPeculiaritiesAdapter().setList(it.aboutTheHotel.peculiarities)
            }
        }
    }

    private fun getPlaceholdersList(size: Int) = (1..size).map {
        ColorDrawable(
            resources.getColor(
                com.arshapshap.hotelapp.designsystem.R.color.light_grey,
                requireContext().theme
            )
        )
    }

    private fun loadImage(url: String, action: (Drawable) -> Unit) {
        val request: ImageRequest = ImageRequest.Builder(requireContext())
            .data(url)
            .target {
                action(it)
            }
            .build()
        requireContext().imageLoader.enqueue(request)
    }

    private fun getImageCarouselAdapter() =
        binding.viewPagerHotelImages.adapter as ImageCarouselAdapter

    private fun getPeculiaritiesAdapter() =
        binding.recyclerViewPeculiarities.adapter as PeculiaritiesAdapter

    private fun Int.format(): String {
        return DecimalFormat("###,###").format(this).replace(',', ' ')
    }
}