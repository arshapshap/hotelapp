package com.arshapshap.hotelapp

import android.app.Application
import com.arshapshap.hotelapp.core.network.networkModule
import com.arshapshap.hotelapp.di.appModule
import com.arshapshap.hotelapp.feature.booking.di.featureBookingModule
import com.arshapshap.hotelapp.feature.hotel.di.featureHotelModule
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                appModule,
                featureHotelModule,
                featureBookingModule,
                networkModule
            )
        }
    }
}