package com.arshapshap.hotelapp.feature.hotel.presentation.common.imagecarousel

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import coil.imageLoader
import coil.request.ImageRequest
import com.arshapshap.hotelapp.designsystem.R

internal class ImageCarouselLoader(private val context: Context, private val adapter: ImageCarouselAdapter) {

    fun loadImages(imageUrls: List<String>) {
        adapter.setList(getPlaceholdersList(imageUrls.size))
        imageUrls.forEachIndexed { index, it ->
            loadImage(url = it) { drawable ->
                adapter.setItem(drawable, index)
            }
        }
    }

    private fun loadImage(url: String, action: (Drawable) -> Unit) {
        val request: ImageRequest = ImageRequest.Builder(context)
            .data(url)
            .target {
                action(it)
            }
            .build()
        context.imageLoader.enqueue(request)
    }

    private fun getPlaceholdersList(size: Int) = (1..size).map {
        ColorDrawable(
            context.resources.getColor(
                R.color.light_grey,
                context.theme
            )
        )
    }
}