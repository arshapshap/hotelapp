package com.arshapshap.hotelapp.feature.booking.presentation.screen.booking

import androidx.core.view.isGone
import com.arshapshap.hotelapp.core.presentation.BaseFragment
import com.arshapshap.hotelapp.core.utils.formatToPrice
import com.arshapshap.hotelapp.feature.booking.R
import com.arshapshap.hotelapp.feature.booking.databinding.FragmentBookingBinding
import com.arshapshap.hotelapp.feature.booking.domain.model.BookingData
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.touristsrecyclerview.TouristsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class BookingFragment : BaseFragment<FragmentBookingBinding, BookingViewModel>(
    FragmentBookingBinding::inflate
){

    override val viewModel: BookingViewModel by viewModel()

    override fun initViews() {
        with(binding) {
            PhoneMaskHelper().addPhoneMask(layoutCustomerInfo.editTextPhoneNumber)

            recyclerViewTourists.adapter = TouristsAdapter(
                onClickExpand = viewModel::expandTouristInfo,
                onFieldChanged = viewModel::onFieldChanged
            )

            imageButtonAddTourist.setOnClickListener {
                viewModel.addTourist()
            }

            buttonPay.setOnClickListener {
                viewModel.clickPay()
            }
        }
    }

    override fun subscribe() {
        viewModel.loadData()

        viewModel.bookingData.observe(viewLifecycleOwner) {
            fillInitialData(it)
        }

        viewModel.tourists.observe(viewLifecycleOwner) {
            binding.layoutAddTourist.isGone = it.size >= viewModel.MAX_TOURISTS

            val recyclerViewState = binding.recyclerViewTourists.layoutManager!!.onSaveInstanceState()
            getTouristsAdapter().setList(it)
            binding.recyclerViewTourists.layoutManager!!.onRestoreInstanceState(recyclerViewState)
        }
    }

    private fun fillInitialData(data: BookingData) {
        with(binding.layoutHeader) {
            tagViewRating.text = getString(R.string.rating, data.horating, data.ratingName)
            textViewHotelName.text = data.hotelName
            textViewAdress.text = data.hotelAdress
        }

        with(binding.layoutMainInfo) {
            textViewDeparture.text = data.departure
            textViewArrivalCountry.text = data.arrivalCountry
            textViewTourDates.text = getString(R.string.tour_dates, data.tourDateStart, data.tourDateStop)
            textViewNumberOfNights.text = getString(R.string.nights, data.numberOfNights)
            textViewHotel.text = data.hotelName
            textViewRoom.text = data.room
            textViewNutrition.text = data.nutrition
        }

        with(binding.layoutReceipt) {
            textViewTourPrice.text = getString(R.string.price, data.tourPrice.formatToPrice())
            textViewFuelCharge.text = getString(R.string.price, data.fuelCharge.formatToPrice())
            textViewServiceCharge.text = getString(R.string.price, data.serviceCharge.formatToPrice())
            textViewForPayment.text = getString(R.string.price, data.sum.formatToPrice())
        }

        with(binding) {
            buttonPay.text = getString(R.string.pay_sum, data.sum.formatToPrice())
        }
    }

    private fun getTouristsAdapter() = binding.recyclerViewTourists.adapter as TouristsAdapter


}