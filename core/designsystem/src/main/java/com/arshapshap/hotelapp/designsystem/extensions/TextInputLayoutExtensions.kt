package com.arshapshap.hotelapp.designsystem.extensions

import android.content.Context
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setError(context: Context, error: Boolean, message: String = "") {
    val colorRes = if (error) com.arshapshap.hotelapp.designsystem.R.color.red
    else com.arshapshap.hotelapp.designsystem.R.color.grey100
    if (boxBackgroundColor != resources.getColor(colorRes, context.theme))
        boxBackgroundColor = resources.getColor(colorRes, context.theme)
    if (error && this.error == null || !error && this.error != null)
        this.error = if (error) message else null
}