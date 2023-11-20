package com.arshapshap.hotelapp.feature.hotel.presentation.screen.hotel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arshapshap.hotelapp.core.presentation.BaseViewModel
import com.arshapshap.hotelapp.feature.hotel.FeatureHotelRouter
import com.arshapshap.hotelapp.feature.hotel.domain.model.Hotel
import com.arshapshap.hotelapp.feature.hotel.domain.usecase.GetHotelUseCase
import kotlinx.coroutines.launch

internal class HotelViewModel(
    private val getHotelUseCase: GetHotelUseCase,
    private val router: FeatureHotelRouter
) : BaseViewModel() {

    private val _hotel = MutableLiveData<Hotel>()
    val hotel: LiveData<Hotel> = _hotel

    fun loadData() {
        viewModelScope.launch {
            val hotel = getHotelUseCase()
            _hotel.postValue(hotel)
        }
    }

    fun clickOnLocation() {

    }

    fun goToRoomSelection() {
        router.openRoomsScreen()
    }
}