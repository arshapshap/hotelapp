package com.arshapshap.hotelapp.feature.hotel.presentation.screen.hotel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arshapshap.hotelapp.core.presentation.BaseViewModel
import com.arshapshap.hotelapp.feature.hotel.FeatureHotelRouter
import com.arshapshap.hotelapp.feature.hotel.domain.model.AboutTheHotel
import com.arshapshap.hotelapp.feature.hotel.domain.model.Hotel

internal class HotelViewModel(
    private val router: FeatureHotelRouter
) : BaseViewModel() {

    private val _hotel = MutableLiveData<Hotel>()
    val hotel: LiveData<Hotel> = _hotel

    fun loadData() {
        _hotel.postValue(
            Hotel(
                1,
                "Лучший пятизвездочный отель в Хургаде, Египет",
                "Madinat Makadi, Safaga Road, Makadi Bay, Египет",
                134268,
                "за тур с перелётом",
                5,
                "Превосходно",
                listOf(
                    "https://www.atorus.ru/sites/default/files/upload/image/News/56149/Club_Priv%C3%A9_by_Belek_Club_House.jpg",
                    "https://deluxe.voyage/useruploads/articles/The_Makadi_Spa_Hotel_02.jpg",
                    "https://deluxe.voyage/useruploads/articles/article_1eb0a64d00.jpg"
                ),
                AboutTheHotel(
                    "Отель VIP-класса с собственными гольф полями. Высокий уровнь сервиса. Рекомендуем для респектабельного отдыха. Отель принимает гостей от 18 лет!",
                    listOf(
                        "Бесплатный Wifi на всей территории отеля",
                        "1 км до пляжа",
                        "Бесплатный фитнес-клуб",
                        "20 км до аэропорта"
                    )
                )
            )
        )
    }

    fun clickOnLocation() {

    }

    fun goToRoomSelection() {
        router.openRoomsScreen()
    }
}