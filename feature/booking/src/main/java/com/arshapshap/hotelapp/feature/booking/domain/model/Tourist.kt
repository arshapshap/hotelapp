package com.arshapshap.hotelapp.feature.booking.domain.model

import java.util.Date

internal data class Tourist(
    val id: Int,
    val name: String,
    val surname: String,
    val birthday: Date,
    val citizenship: String,
    val passportNumber: Int,
    val passportValidityPeriod: Date
)
