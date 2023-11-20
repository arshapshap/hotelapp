package com.arshapshap.hotelapp.feature.booking.presentation.screen.booking

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arshapshap.hotelapp.core.presentation.BaseViewModel
import com.arshapshap.hotelapp.feature.booking.FeatureBookingRouter
import com.arshapshap.hotelapp.feature.booking.domain.model.BookingData
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.BookingError
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.Customer
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.EditableTourist
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.EditableTouristField
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

internal class BookingViewModel(
    private val router: FeatureBookingRouter
) : BaseViewModel() {

    val MAX_TOURISTS = 5

    private val _bookingData = MutableLiveData<BookingData>()
    val bookingData: LiveData<BookingData> = _bookingData

    private val _customer = MutableLiveData<Customer>(Customer("", ""))
    val customer: LiveData<Customer> = _customer

    private val _touristsList: MutableList<EditableTourist> = mutableListOf(getNewTourist())
    private val _tourists = MutableLiveData<List<EditableTourist>>(_touristsList)
    val tourists: LiveData<List<EditableTourist>> = _tourists

    private val _errors = MutableLiveData<Set<BookingError>>(setOf())
    val errors: LiveData<Set<BookingError>> = _errors

    fun loadData() {
        _bookingData.value = BookingData(
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
    }

    fun clickPay() {
        if (checkValidity()) {
            val orderId = (0..12345).random()
            router.openSuccessPage(orderId)
        }
    }

    fun addTourist() {
        val newTourist = getNewTourist()
        _touristsList.add(newTourist)
        _tourists.value = _touristsList
    }

    fun expandTouristInfo(id: Int) {
        _touristsList.let {
            it[id] = it[id].copy(isExpanded = !it[id].isExpanded)
        }
        _tourists.value = _touristsList
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        _errors.value = _errors.value!!.minus(BookingError.WrongPhone)
        _customer.value = _customer.value?.copy(phoneNumber = phoneNumber)
    }

    fun onEmailChanged(email: String) {
        _errors.value = _errors.value!!.minus(BookingError.WrongEmail)
        _customer.value = _customer.value?.copy(email = email)
    }

    fun validateEmail() {
        val email = customer.value?.email ?: ""
        if (!email.isEmailValid())
            _errors.value = _errors.value!!.plus(BookingError.WrongEmail)
    }

    fun onTouristFieldChanged(id: Int, field: EditableTouristField, value: String) {
        _touristsList.let {
            val tourist = it[id]
            val new = when (field) {
                EditableTouristField.Name -> {
                    val new = tourist.copy(name = value)
                    isTouristNameValid(new)
                    new
                }
                EditableTouristField.Surname -> {
                    val new = tourist.copy(surname = value)
                    isTouristSurnameValid(new)
                    new
                }
                EditableTouristField.Birthday -> {
                    val new = tourist.copy(birthday = value.tryConvertToDate())
                    isTouristBirthdayValid(new)
                    new
                }
                EditableTouristField.Citizenship -> {
                    val new = tourist.copy(citizenship = value)
                    isTouristCitizenshipValid(new)
                    new
                }
                EditableTouristField.PassportNumber -> {
                    val new = tourist.copy(passportNumber = value.toIntOrNull())
                    isTouristPassportNumberValid(new)
                    new
                }
                EditableTouristField.PassportValidityPeriod -> {
                    val new = tourist.copy(passportValidityPeriod = value.tryConvertToDate())
                    isTouristPassportValidityPeriodValid(new)
                    new
                }
            }

            it[id] = new
        }
    }

    private fun isTouristNameValid(tourist: EditableTourist): Boolean {
        if (tourist.name == "")
            _errors.value = _errors.value!!.plus(BookingError.WrongName(tourist.id))
        else
            _errors.value = _errors.value!!.minus(BookingError.WrongName(tourist.id))
        return tourist.name != ""
    }

    private fun isTouristSurnameValid(tourist: EditableTourist): Boolean {
        if (tourist.surname == "")
            _errors.value = _errors.value!!.plus(BookingError.WrongSurname(tourist.id))
        else
            _errors.value = _errors.value!!.minus(BookingError.WrongSurname(tourist.id))
        return tourist.surname != ""
    }

    private fun isTouristBirthdayValid(tourist: EditableTourist): Boolean {
        if (tourist.birthday == null)
            _errors.value = _errors.value!!.plus(BookingError.WrongBirthday(tourist.id))
        else
            _errors.value = _errors.value!!.minus(BookingError.WrongBirthday(tourist.id))
        return tourist.birthday != null
    }

    private fun isTouristCitizenshipValid(tourist: EditableTourist): Boolean {
        if (tourist.citizenship == "")
            _errors.value = _errors.value!!.plus(BookingError.WrongCitizenship(tourist.id))
        else
            _errors.value = _errors.value!!.minus(BookingError.WrongCitizenship(tourist.id))
        return tourist.citizenship != ""
    }

    private fun isTouristPassportNumberValid(tourist: EditableTourist): Boolean {
        if (tourist.passportNumber == null)
            _errors.value = _errors.value!!.plus(BookingError.WrongPassportNumber(tourist.id))
        else
            _errors.value = _errors.value!!.minus(BookingError.WrongPassportNumber(tourist.id))
        return tourist.passportNumber != null
    }

    private fun isTouristPassportValidityPeriodValid(tourist: EditableTourist): Boolean {
        if (tourist.passportValidityPeriod == null)
            _errors.value = _errors.value!!.plus(BookingError.WrongPassportValidityPeriod(tourist.id))
        else
            _errors.value = _errors.value!!.minus(BookingError.WrongPassportValidityPeriod(tourist.id))
        return tourist.passportValidityPeriod != null
    }

    private fun checkValidity(): Boolean {
        var error = false
        if (!customer.value!!.email.isEmailValid()){
            error = true
            _errors.value = _errors.value!!.plus(BookingError.WrongEmail)
        }
        if (!customer.value!!.phoneNumber.isPhoneValid()) {
            error = true
            _errors.value = _errors.value!!.plus(BookingError.WrongPhone)
        }
        _touristsList.forEach {

        }

        return !error
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

    private fun String.isPhoneValid(): Boolean {
        return this.length == 10
    }

    private fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun String.tryConvertToDate(): LocalDate? {
        return try {
            val readingFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            LocalDate.parse(this, readingFormatter)
        } catch (e: DateTimeParseException) {
            null
        }
    }
}