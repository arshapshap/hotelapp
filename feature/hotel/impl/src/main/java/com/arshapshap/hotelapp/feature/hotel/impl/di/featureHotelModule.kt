package com.arshapshap.hotelapp.feature.hotel.impl.di

import com.arshapshap.hotelapp.feature.hotel.impl.presentation.screen.hotel.HotelViewModel
import com.arshapshap.hotelapp.feature.hotel.impl.presentation.screen.rooms.RoomsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureHotelModule = module {

    // presentation
    viewModel<HotelViewModel> { HotelViewModel(get()) }
    viewModel<RoomsViewModel> { RoomsViewModel(get()) }
}