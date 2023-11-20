package com.arshapshap.hotelapp.feature.booking.di

import com.arshapshap.hotelapp.feature.booking.data.mapper.BookingMapper
import com.arshapshap.hotelapp.feature.booking.data.network.BookingApi
import com.arshapshap.hotelapp.feature.booking.data.repository.BookingRepositoryImpl
import com.arshapshap.hotelapp.feature.booking.domain.repository.BookingRepository
import com.arshapshap.hotelapp.feature.booking.domain.usecase.GetBookingDataUseCase
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.BookingViewModel
import com.arshapshap.hotelapp.feature.booking.presentation.screen.success.SuccessViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val featureBookingModule = module {

    // data
    factory<BookingApi> { get<Retrofit>().create(BookingApi::class.java) }

    factory<BookingMapper> { BookingMapper() }

    factory<BookingRepository> { BookingRepositoryImpl(get(), get()) }

    // domain
    factory<GetBookingDataUseCase> { GetBookingDataUseCase(get()) }

    // presentation
    viewModel<BookingViewModel> { BookingViewModel(get(), get()) }
    viewModel<SuccessViewModel> { params -> SuccessViewModel(params.get(), get()) }
}