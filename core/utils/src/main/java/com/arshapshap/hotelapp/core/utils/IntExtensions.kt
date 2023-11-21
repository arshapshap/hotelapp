package com.arshapshap.hotelapp.core.utils

import java.text.DecimalFormat

fun Int.formatToPrice(): String {
    return DecimalFormat("###,###").format(this).replace(',', ' ')
}