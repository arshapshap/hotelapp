package com.arshapshap.hotelapp.designsystem.extensions

import android.content.Context
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setError(context: Context, error: Boolean, message: String = "") {
    val colorRes = if (error) com.arshapshap.hotelapp.designsystem.R.color.red
    else com.arshapshap.hotelapp.designsystem.R.color.grey100
    boxBackgroundColor = resources.getColor(colorRes, context.theme)
    this.error = if (error) message else null
}