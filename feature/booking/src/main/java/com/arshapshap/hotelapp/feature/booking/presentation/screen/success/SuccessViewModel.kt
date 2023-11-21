package com.arshapshap.hotelapp.feature.booking.presentation.screen.success

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arshapshap.hotelapp.core.presentation.BaseViewModel
import com.arshapshap.hotelapp.feature.booking.FeatureBookingRouter

internal class SuccessViewModel(
    orderId: Int,
    private val router: FeatureBookingRouter
) : BaseViewModel() {

    private val _order = MutableLiveData(orderId)
    val order: LiveData<Int> = _order

    fun clickSuper() {
        router.openHotelScreen()
    }
}