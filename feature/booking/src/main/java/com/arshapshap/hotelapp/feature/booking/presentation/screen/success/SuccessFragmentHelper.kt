package com.arshapshap.hotelapp.feature.booking.presentation.screen.success

import android.os.Bundle
import androidx.core.os.bundleOf

object SuccessFragmentHelper {

    fun createBundle(orderId: Int): Bundle {
        return bundleOf(ORDER_ID_KEY to orderId)
    }

    internal const val ORDER_ID_KEY = "ORDER_ID_KEY"
}