package com.arshapshap.hotelapp.feature.booking.presentation.screen.booking

import androidx.core.view.isGone
import androidx.core.widget.doAfterTextChanged
import com.arshapshap.hotelapp.core.presentation.BaseFragment
import com.arshapshap.hotelapp.core.utils.formatToPrice
import com.arshapshap.hotelapp.designsystem.extensions.setError
import com.arshapshap.hotelapp.feature.booking.R
import com.arshapshap.hotelapp.feature.booking.databinding.FragmentBookingBinding
import com.arshapshap.hotelapp.feature.booking.domain.model.BookingData
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.BookingError
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.touristsrecyclerview.TouristsAdapter
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class BookingFragment : BaseFragment<FragmentBookingBinding, BookingViewModel>(
    FragmentBookingBinding::inflate
){

    override val viewModel: BookingViewModel by viewModel()

    override fun initViews() {
        with(binding.layoutCustomerInfo) {
            PhoneMaskHelper().addPhoneMask(
                editTextPhoneNumber,
                viewModel::onPhoneNumberChanged
            )
            editTextPhoneNumber.doAfterTextChanged {
                textInputPhoneNumber.setError(false)
            }

            editTextEmail.doAfterTextChanged {
                textInputEmail.setError(false)
                viewModel.onEmailChanged(it.toString())
            }
            editTextEmail.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus)
                    viewModel.validateEmail()
            }
        }

        with(binding) {
            recyclerViewTourists.adapter = TouristsAdapter(
                onClickExpand = viewModel::expandTouristInfo,
                onFieldChanged = viewModel::onTouristFieldChanged
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
            binding.rootLinearLayout.isGone = false
            fillInitialData(it)
        }

        viewModel.tourists.observe(viewLifecycleOwner) {
            binding.layoutAddTourist.isGone = it.size >= viewModel.MAX_TOURISTS

            val recyclerViewState = binding.recyclerViewTourists.layoutManager!!.onSaveInstanceState()
            getTouristsAdapter().setList(it)
            binding.recyclerViewTourists.layoutManager!!.onRestoreInstanceState(recyclerViewState)
        }

        viewModel.errors.observe(viewLifecycleOwner) { errors ->
            when {
                BookingError.WrongPhone in errors -> {
                    binding.layoutCustomerInfo.textInputPhoneNumber.setError(true, getString(R.string.wrong_phone_number))
                }
                BookingError.WrongEmail in errors -> {
                    binding.layoutCustomerInfo.textInputEmail.setError(true, getString(R.string.wrong_email))
                }
            }
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

    private fun TextInputLayout.setError(error: Boolean, message: String = "") {
        this.setError(requireContext(), error, message)
    }
}