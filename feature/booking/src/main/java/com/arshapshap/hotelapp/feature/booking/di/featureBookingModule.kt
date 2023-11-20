package com.arshapshap.hotelapp.feature.booking.di

import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.BookingViewModel
import com.arshapshap.hotelapp.feature.booking.presentation.screen.success.SuccessViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureBookingModule = module {

    // presentation
    viewModel<BookingViewModel> { BookingViewModel(get()) }
    viewModel<SuccessViewModel> { params -> SuccessViewModel(params.get(), get()) }
}