package com.arshapshap.hotelapp.feature.booking.presentation.screen.booking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arshapshap.hotelapp.core.presentation.BaseViewModel
import com.arshapshap.hotelapp.feature.booking.FeatureBookingRouter
import com.arshapshap.hotelapp.feature.booking.domain.model.BookingData
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.Customer
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.EditableTourist
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.EditableTouristField

internal class BookingViewModel(
    private val router: FeatureBookingRouter
) : BaseViewModel() {

    val MAX_TOURISTS = 5

    private val _bookingData = MutableLiveData<BookingData>()
    val bookingData: LiveData<BookingData> = _bookingData

    private val _customer = MutableLiveData<Customer>()
    val customer: LiveData<Customer> = _customer

    private val _touristsList: MutableList<EditableTourist> = mutableListOf(getNewTourist())

    private val _tourists = MutableLiveData<List<EditableTourist>>(_touristsList)
    val tourists: LiveData<List<EditableTourist>> = _tourists

    fun loadData() {
        _bookingData.postValue(
            BookingData(
                id = 0,
                hotelName = "Лучший пятизвездочный отель в Хургаде, Египет",
                hotelAdress = "Madinat Makadi, Safaga Road, Makadi Bay, Египет",
                horating = 5,
                ratingName = "Превосходно",
                departure = "Москва",
                arrivalCountry = "Египет, Хургада",
                tourDateStart = "19.09.2023",
                tourDateStop = "27.09.2023",
                numberOfNights = 7,
                room = "Люкс номер с видом на море",
                nutrition = "Все включено",
                tourPrice = 289400,
                fuelCharge = 9300,
                serviceCharge = 2150,
                sum = 289400+9300+2150
            )
        )
    }

    fun clickPay() {
        val orderId = (0..12345).random()
        router.openSuccessPage(orderId)
    }

    fun addTourist() {
        val newTourist = getNewTourist()
        _touristsList.add(newTourist)
        _tourists.postValue(_touristsList)
    }

    fun expandTouristInfo(id: Int) {
        _touristsList.let {
            it[id] = it[id].copy(isExpanded = !it[id].isExpanded)
        }
        _tourists.postValue(_touristsList)
    }

    fun onFieldChanged(id: Int, field: EditableTouristField, value: String) {
        _touristsList.let {
            val tourist = it[id]
            val new = when (field) {
                EditableTouristField.Name -> tourist.copy(name = value)
                EditableTouristField.Surname -> tourist.copy(surname = value)
                EditableTouristField.Birthday -> tourist
                EditableTouristField.Citizenship -> tourist.copy(citizenship = value)
                EditableTouristField.PassportNumber -> tourist.copy(passportNumber = value.toIntOrNull())
                EditableTouristField.PassportValidityPeriod -> tourist
            }

            it[id] = new
        }
    }

    private fun getNewTourist() = EditableTourist(
        id = _touristsList?.size ?: 0,
        name = "",
        surname = "",
        birthday = null,
        citizenship = "",
        passportNumber = null,
        passportValidityPeriod = null
    )
}