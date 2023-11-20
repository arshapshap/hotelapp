package com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model

internal sealed interface BookingError {
    data object WrongPhone : BookingError

    data object WrongEmail : BookingError

    data class WrongName(val touristId: Int) : BookingError

    data class WrongSurname(val touristId: Int) : BookingError

    data class WrongBirthday(val touristId: Int) : BookingError

    data class WrongCitizenship(val touristId: Int) : BookingError

    data class WrongPassportNumber(val touristId: Int) : BookingError

    data class WrongPassportValidityPeriod(val touristId: Int) : BookingError
}