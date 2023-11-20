package com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model

import java.util.Date

data class EditableTourist(
    val id: Int,
    val name: String,
    val surname: String,
    val birthday: Date?,
    val citizenship: String,
    val passportNumber: Int?,
    val passportValidityPeriod: Date?,
    val isExpanded: Boolean = true
)