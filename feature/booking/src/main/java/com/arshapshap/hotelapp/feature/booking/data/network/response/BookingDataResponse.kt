package com.arshapshap.hotelapp.feature.booking.data.network.response


import com.google.gson.annotations.SerializedName

data class BookingDataResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("hotel_name")
    val hotelName: String?,
    @SerializedName("hotel_adress")
    val hotelAdress: String?,
    @SerializedName("horating")
    val horating: Int?,
    @SerializedName("rating_name")
    val ratingName: String?,
    @SerializedName("departure")
    val departure: String?,
    @SerializedName("arrival_country")
    val arrivalCountry: String?,
    @SerializedName("tour_date_start")
    val tourDateStart: String?,
    @SerializedName("tour_date_stop")
    val tourDateStop: String?,
    @SerializedName("number_of_nights")
    val numberOfNights: Int?,
    @SerializedName("room")
    val room: String?,
    @SerializedName("nutrition")
    val nutrition: String?,
    @SerializedName("tour_price")
    val tourPrice: Int?,
    @SerializedName("fuel_charge")
    val fuelCharge: Int?,
    @SerializedName("service_charge")
    val serviceCharge: Int?
)