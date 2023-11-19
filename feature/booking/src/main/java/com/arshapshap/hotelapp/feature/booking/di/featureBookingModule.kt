package com.arshapshap.hotelapp.feature.booking.di

import com.arshapshap.hotelapp.feature.booking.presentation.screen.success.SuccessViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureBookingModule = module {

    // presentation
    viewModel<SuccessViewModel> { params -> SuccessViewModel(params.get(), get()) }
}