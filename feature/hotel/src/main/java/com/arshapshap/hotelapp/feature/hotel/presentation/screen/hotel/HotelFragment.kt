package com.arshapshap.hotelapp.feature.hotel.presentation.screen.hotel

import com.arshapshap.hotelapp.core.presentation.BaseFragment
import com.arshapshap.hotelapp.feature.hotel.R
import com.arshapshap.hotelapp.feature.hotel.databinding.FragmentHotelBinding
import com.arshapshap.hotelapp.feature.hotel.presentation.common.imagecarousel.ImageCarouselAdapter
import com.arshapshap.hotelapp.feature.hotel.presentation.common.imagecarousel.ImageCarouselLoader
import com.arshapshap.hotelapp.feature.hotel.presentation.common.peculiaritiesrecyclerview.PeculiaritiesAdapter
import com.arshapshap.hotelapp.feature.hotel.presentation.common.utils.formatToPrice
import org.koin.androidx.viewmodel.ext.android.viewModel


internal class HotelFragment : BaseFragment<FragmentHotelBinding, HotelViewModel>(
    FragmentHotelBinding::inflate
) {

    override val viewModel: HotelViewModel by viewModel()

    override fun initViews() {
        with(binding) {
            layoutImagesCarousel.viewPagerImages.adapter = ImageCarouselAdapter()
            recyclerViewPeculiarities.root.adapter = PeculiaritiesAdapter()

            textViewLocation.setOnClickListener {
                viewModel.clickOnLocation()
            }
            buttonGoToRoomSelection.setOnClickListener {
                viewModel.goToRoomSelection()
            }
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
            }
        }
    }

    private fun getImageCarouselAdapter() =
        binding.layoutImagesCarousel.viewPagerImages.adapter as ImageCarouselAdapter

    private fun getPeculiaritiesAdapter() =
        binding.recyclerViewPeculiarities.root.adapter as PeculiaritiesAdapter
}