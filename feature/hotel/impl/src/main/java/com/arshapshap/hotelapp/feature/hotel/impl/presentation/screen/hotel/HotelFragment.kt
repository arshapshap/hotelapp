package com.arshapshap.hotelapp.feature.hotel.impl.presentation.screen.hotel

import com.arshapshap.hotelapp.core.presentation.BaseFragment
import com.arshapshap.hotelapp.feature.hotel.impl.R
import com.arshapshap.hotelapp.feature.hotel.impl.databinding.FragmentHotelBinding
import com.arshapshap.hotelapp.feature.hotel.impl.presentation.common.imagecarousel.ImageCarouselAdapter
import com.arshapshap.hotelapp.feature.hotel.impl.presentation.common.imagecarousel.ImageCarouselLoader
import com.arshapshap.hotelapp.feature.hotel.impl.presentation.common.peculiaritiesrecyclerview.PeculiaritiesAdapter
import com.arshapshap.hotelapp.feature.hotel.impl.presentation.common.utils.formatToPrice


internal class HotelFragment : BaseFragment<FragmentHotelBinding, HotelViewModel>(
    FragmentHotelBinding::inflate
) {

    override val viewModel: HotelViewModel by lazy {
        HotelViewModel()
    }

    override fun initViews() {
        with(binding) {
            layoutImagesCarousel.viewPagerImages.adapter = ImageCarouselAdapter()

            recyclerViewPeculiarities.root.adapter = PeculiaritiesAdapter()
        }
    }

    override fun subscribe() {
        viewModel.loadData()

        viewModel.hotel.observe(viewLifecycleOwner) {
            with(binding) {
                ImageCarouselLoader(
                    context = requireContext(),
                    adapter = getImageCarouselAdapter()
                ).loadImages(it.imageUrls)

                tagViewRating.text = resources.getString(R.string.rating, it.rating, it.ratingName)
                textViewHotelName.text = it.name
                textViewLocation.text = it.adress
                textViewPrice.text = resources.getString(R.string.minimal_price, it.minimalPrice.formatToPrice())
                textViewPriceDescription.text = it.priceForIt
                textViewAboutHotel.text = it.aboutTheHotel.description
                getPeculiaritiesAdapter().setList(it.aboutTheHotel.peculiarities)

                buttonGoToRoomSelection.setOnClickListener {
                    viewModel.goToRoomSelection()
                }
            }
        }
    }

    private fun getImageCarouselAdapter() =
        binding.layoutImagesCarousel.viewPagerImages.adapter as ImageCarouselAdapter

    private fun getPeculiaritiesAdapter() =
        binding.recyclerViewPeculiarities.root.adapter as PeculiaritiesAdapter
}