package com.arshapshap.hotelapp.designsystem

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.setPadding


class MenuItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        View.inflate(context, R.layout.layout_menu_item, this)

        setDefaultValues()

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MenuItemView)
        try {
            setValuesFromAttributes(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    private fun setDefaultValues() {
        gravity = Gravity.CENTER_VERTICAL
        isClickable = true
        setPadding(resources.getDimensionPixelOffset(R.dimen.menu_item_view_padding))
        setBackgroundResource(R.drawable.shape_normal_rounded_rectangle)
        background.setTint(resources.getColor(R.color.light_grey, context.theme))
    }

    private fun setValuesFromAttributes(typedArray: TypedArray) {
        val drawableStartId = typedArray.getResourceId(R.styleable.MenuItemView_drawable, 0)
        findViewById<ImageView>(R.id.imageViewIconStart)?.setImageResource(drawableStartId)

        val headlineText = typedArray.getString(R.styleable.MenuItemView_headlineText)
        findViewById<TextView>(R.id.textViewHeadline)?.text = headlineText

        val descriptionText = typedArray.getString(R.styleable.MenuItemView_descriptionText)
        findViewById<TextView>(R.id.textViewDescription)?.text = descriptionText
    }
}