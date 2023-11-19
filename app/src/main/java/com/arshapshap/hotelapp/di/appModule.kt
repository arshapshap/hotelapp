package com.arshapshap.hotelapp.di

import com.arshapshap.hotelapp.feature.hotel.impl.FeatureHotelRouter
import com.arshapshap.hotelapp.navigation.Navigator
import org.koin.dsl.module

val appModule = module {

    // navigation
    single<Navigator> { Navigator() }
    single<FeatureHotelRouter> { get<Navigator>() }

}