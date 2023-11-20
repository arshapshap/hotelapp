package com.arshapshap.hotelapp.feature.hotel.di

import com.arshapshap.hotelapp.feature.hotel.data.mapper.HotelMapper
import com.arshapshap.hotelapp.feature.hotel.data.mapper.RoomMapper
import com.arshapshap.hotelapp.feature.hotel.data.network.hotel.HotelApi
import com.arshapshap.hotelapp.feature.hotel.data.network.room.RoomApi
import com.arshapshap.hotelapp.feature.hotel.data.repository.HotelRepositoryImpl
import com.arshapshap.hotelapp.feature.hotel.data.repository.RoomRepositoryImpl
import com.arshapshap.hotelapp.feature.hotel.domain.repository.HotelRepository
import com.arshapshap.hotelapp.feature.hotel.domain.repository.RoomRepository
import com.arshapshap.hotelapp.feature.hotel.domain.usecase.GetHotelUseCase
import com.arshapshap.hotelapp.feature.hotel.domain.usecase.GetRoomsUseCase
import com.arshapshap.hotelapp.feature.hotel.presentation.screen.hotel.HotelViewModel
import com.arshapshap.hotelapp.feature.hotel.presentation.screen.rooms.RoomsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val featureHotelModule = module {

    // data
    factory<HotelApi> { get<Retrofit>().create(HotelApi::class.java) }
    factory<RoomApi> { get<Retrofit>().create(RoomApi::class.java) }

    factory<HotelMapper> { HotelMapper() }
    factory<RoomMapper> { RoomMapper() }

    factory<HotelRepository> { HotelRepositoryImpl(get(), get()) }
    factory<RoomRepository> { RoomRepositoryImpl(get(), get()) }

    // domain
    factory<GetHotelUseCase> { GetHotelUseCase(get()) }
    factory<GetRoomsUseCase> { GetRoomsUseCase(get()) }

    // presentation
    viewModel<HotelViewModel> { HotelViewModel(get(), get()) }
    viewModel<RoomsViewModel> { RoomsViewModel(get(), get()) }
}