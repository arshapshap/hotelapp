package com.arshapshap.hotelapp

import android.app.Application
import com.arshapshap.hotelapp.di.appModule
import com.arshapshap.hotelapp.feature.hotel.impl.di.featureHotelModule
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                appModule,
                featureHotelModule
            )
        }
    }
}