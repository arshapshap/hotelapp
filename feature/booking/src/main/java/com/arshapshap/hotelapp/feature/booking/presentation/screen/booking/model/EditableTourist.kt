package com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model

import java.time.LocalDate

internal data class EditableTourist(
    val id: Int,
    val name: String,
    val surname: String,
    val birthday: LocalDate?,
    val citizenship: String,
    val passportNumber: Int?,
    val passportValidityPeriod: LocalDate?,
    val isExpanded: Boolean = true,
    val wrongName: Boolean = false,
    val wrongSurname: Boolean = false,
    val wrongBirthday: Boolean = false,
    val wrongCitizenship: Boolean = false,
    val wrongPassportNumber: Boolean = false,
    val wrongPassportValidityPeriod: Boolean = false,
)