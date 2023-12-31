package com.arshapshap.hotelapp.navigation

import androidx.navigation.NavController
import com.arshapshap.hotelapp.R
import com.arshapshap.hotelapp.feature.booking.FeatureBookingRouter
import com.arshapshap.hotelapp.feature.booking.presentation.screen.success.SuccessFragmentHelper
import com.arshapshap.hotelapp.feature.hotel.FeatureHotelRouter

class Navigator : FeatureHotelRouter, FeatureBookingRouter {

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

    override fun openHotelScreen() {
        navController?.navigate(R.id.action_successFragment_to_hotelFragment)
    }

    override fun openRoomsScreen() {
        navController?.navigate(R.id.action_hotelFragment_to_roomsFragment)
    }

    override fun openBookingScreen(roomId: Int) {
        navController?.navigate(R.id.action_roomsFragment_to_bookingFragment)
    }

    override fun openSuccessPage(orderId: Int) {
        navController?.navigate(
            R.id.action_bookingFragment_to_successFragment,
            SuccessFragmentHelper.createBundle(orderId)
        )
    }
}