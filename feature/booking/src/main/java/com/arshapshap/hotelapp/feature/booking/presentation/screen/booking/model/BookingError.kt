package com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model

internal sealed interface BookingError {
    data object WrongPhone : BookingError

    data object WrongEmail : BookingError

    sealed interface Tourist {

        val touristId: Int

        data class WrongName(override val touristId: Int) : BookingError, Tourist

        data class WrongSurname(override val touristId: Int) : BookingError, Tourist

        data class WrongBirthday(override val touristId: Int) : BookingError, Tourist

        data class WrongCitizenship(override val touristId: Int) : BookingError, Tourist

        data class WrongPassportNumber(override val touristId: Int) : BookingError, Tourist

        data class WrongPassportValidityPeriod(override val touristId: Int) : BookingError, Tourist
    }
}