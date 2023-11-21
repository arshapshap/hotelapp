package com.arshapshap.hotelapp.feature.booking.presentation.screen.booking

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arshapshap.hotelapp.core.presentation.BaseViewModel
import com.arshapshap.hotelapp.feature.booking.FeatureBookingRouter
import com.arshapshap.hotelapp.feature.booking.domain.model.BookingData
import com.arshapshap.hotelapp.feature.booking.domain.usecase.GetBookingDataUseCase
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.BookingError
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.Customer
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.EditableTourist
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.EditableTouristField
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

internal class BookingViewModel(
    private val getBookingDataUseCase: GetBookingDataUseCase,
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
        viewModelScope.launch {
            val bookingData = getBookingDataUseCase()
            _bookingData.postValue(bookingData)
        }
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
        removeError(BookingError.WrongPhone)
        _customer.value = _customer.value?.copy(phoneNumber = phoneNumber)
    }

    fun onEmailChanged(email: String) {
        removeError(BookingError.WrongEmail)
        _customer.value = _customer.value?.copy(email = email)
    }

    fun validateEmail() {
        val email = customer.value?.email ?: ""
        if (!email.isEmailValid())
            addError(BookingError.WrongEmail)
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
            addError(BookingError.Tourist.WrongName(tourist.id))
        else
            removeError(BookingError.Tourist.WrongName(tourist.id))
        return tourist.name != ""
    }

    private fun isTouristSurnameValid(tourist: EditableTourist): Boolean {
        if (tourist.surname == "")
            addError(BookingError.Tourist.WrongSurname(tourist.id))
        else
            removeError(BookingError.Tourist.WrongSurname(tourist.id))
        return tourist.surname != ""
    }

    private fun isTouristBirthdayValid(tourist: EditableTourist): Boolean {
        if (tourist.birthday == null)
            addError(BookingError.Tourist.WrongBirthday(tourist.id))
        else
            removeError(BookingError.Tourist.WrongBirthday(tourist.id))
        return tourist.birthday != null
    }

    private fun isTouristCitizenshipValid(tourist: EditableTourist): Boolean {
        if (tourist.citizenship == "")
            addError(BookingError.Tourist.WrongCitizenship(tourist.id))
        else
            removeError(BookingError.Tourist.WrongCitizenship(tourist.id))
        return tourist.citizenship != ""
    }

    private fun isTouristPassportNumberValid(tourist: EditableTourist): Boolean {
        if (tourist.passportNumber == null)
            addError(BookingError.Tourist.WrongPassportNumber(tourist.id))
        else
            removeError(BookingError.Tourist.WrongPassportNumber(tourist.id))
        return tourist.passportNumber != null
    }

    private fun isTouristPassportValidityPeriodValid(tourist: EditableTourist): Boolean {
        if (tourist.passportValidityPeriod == null)
            addError(BookingError.Tourist.WrongPassportValidityPeriod(tourist.id))
        else
            removeError(BookingError.Tourist.WrongPassportValidityPeriod(tourist.id))
        return tourist.passportValidityPeriod != null
    }

    private fun checkValidity(): Boolean {
        var error = false
        if (!customer.value!!.email.isEmailValid()){
            error = true
            addError(BookingError.WrongEmail)
        }
        if (!customer.value!!.phoneNumber.isPhoneValid()) {
            error = true
            addError(BookingError.WrongPhone)
        }
        _touristsList.forEach {
            error = !isTouristNameValid(it) || error
            error = !isTouristSurnameValid(it) || error
            error = !isTouristBirthdayValid(it) || error
            error = !isTouristCitizenshipValid(it) || error
            error = !isTouristPassportNumberValid(it) || error
            error = !isTouristPassportValidityPeriodValid(it) || error
        }

        _tourists.value = _touristsList
        return !error
    }

    private fun addError(error: BookingError) {
        if (error !is BookingError.Tourist && error !in _errors.value!!)
            _errors.value = _errors.value!!.plus(error)
        else if (error is BookingError.Tourist) {
            _touristsList[error.touristId] = when(error) {
                is BookingError.Tourist.WrongName ->
                    _touristsList[error.touristId].copy(wrongName = true)
                is BookingError.Tourist.WrongSurname ->
                    _touristsList[error.touristId].copy(wrongSurname = true)
                is BookingError.Tourist.WrongBirthday ->
                    _touristsList[error.touristId].copy(wrongBirthday = true)
                is BookingError.Tourist.WrongCitizenship ->
                    _touristsList[error.touristId].copy(wrongCitizenship = true)
                is BookingError.Tourist.WrongPassportNumber ->
                    _touristsList[error.touristId].copy(wrongPassportNumber = true)
                is BookingError.Tourist.WrongPassportValidityPeriod ->
                    _touristsList[error.touristId].copy(wrongPassportValidityPeriod = true)
            }
        }
    }

    private fun removeError(error: BookingError) {
        if (error !is BookingError.Tourist && error in _errors.value!!)
            _errors.value = _errors.value!!.minus(error)
        else if (error is BookingError.Tourist) {
            _touristsList[error.touristId] = when(error) {
                is BookingError.Tourist.WrongName ->
                    _touristsList[error.touristId].copy(wrongName = false)
                is BookingError.Tourist.WrongSurname ->
                    _touristsList[error.touristId].copy(wrongSurname = false)
                is BookingError.Tourist.WrongBirthday ->
                    _touristsList[error.touristId].copy(wrongBirthday = false)
                is BookingError.Tourist.WrongCitizenship ->
                    _touristsList[error.touristId].copy(wrongCitizenship = false)
                is BookingError.Tourist.WrongPassportNumber ->
                    _touristsList[error.touristId].copy(wrongPassportNumber = false)
                is BookingError.Tourist.WrongPassportValidityPeriod ->
                    _touristsList[error.touristId].copy(wrongPassportValidityPeriod = false)
            }
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