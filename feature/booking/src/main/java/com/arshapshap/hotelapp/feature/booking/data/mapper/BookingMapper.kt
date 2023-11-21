package com.arshapshap.hotelapp.feature.booking.data.mapper

import com.arshapshap.hotelapp.feature.booking.data.network.response.BookingDataResponse
import com.arshapshap.hotelapp.feature.booking.domain.model.BookingData

internal class BookingMapper() {

    fun map(bookingDataResponse: BookingDataResponse): BookingData = with(bookingDataResponse) {
        BookingData(
            id = id ?: 0,
            hotelName = hotelName ?: "",
            hotelAdress = hotelAdress ?: "",
            horating = horating ?: 0,
            ratingName = ratingName ?: "",
            departure = departure ?: "",
            arrivalCountry = arrivalCountry ?: "",
            tourDateStart = tourDateStart ?: "",
            tourDateStop = tourDateStop ?: "",
            numberOfNights = numberOfNights ?: 0,
            room = room ?: "",
            nutrition = nutrition ?: "",
            tourPrice = tourPrice ?: 0,
            fuelCharge = fuelCharge ?: 0,
            serviceCharge = serviceCharge ?: 0,
            sum = (tourPrice ?: 0) + (fuelCharge ?: 0) + (serviceCharge ?: 0),
        )
    }
}