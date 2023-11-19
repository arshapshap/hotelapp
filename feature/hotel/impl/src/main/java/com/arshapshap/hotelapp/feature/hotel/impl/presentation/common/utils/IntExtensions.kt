package com.arshapshap.hotelapp.feature.hotel.impl.presentation.common.utils

import java.text.DecimalFormat

internal fun Int.formatToPrice(): String {
    return DecimalFormat("###,###").format(this).replace(',', ' ')
}