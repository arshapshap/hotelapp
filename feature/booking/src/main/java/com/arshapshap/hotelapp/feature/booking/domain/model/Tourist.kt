package com.arshapshap.hotelapp.feature.booking.domain.model

import java.time.LocalDate

internal data class Tourist(
    val id: Int,
    val name: String,
    val surname: String,
    val birthday: LocalDate,
    val citizenship: String,
    val passportNumber: Int,
    val passportValidityPeriod: LocalDate
)
