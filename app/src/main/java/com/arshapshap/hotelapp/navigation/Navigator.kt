package com.arshapshap.hotelapp.navigation

import androidx.navigation.NavController
import com.arshapshap.hotelapp.feature.hotel.impl.FeatureHotelRouter

class Navigator : FeatureHotelRouter {

    private var navController: NavController? = null

    fun attachNavController(navController: NavController, graph: Int) {
        navController.setGraph(graph)
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        if (this.navController == navController) {
            this.navController = null
        }
    }

    override fun openRoomsList() {
        navController?.navigate(com.arshapshap.hotelapp.feature.hotel.impl.R.id.roomsFragment)
    }

    override fun openBookingRoom(roomId: Int) {
        //TODO("Not yet implemented")
    }
}