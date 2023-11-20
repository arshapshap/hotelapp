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
    val isExpanded: Boolean = true
)