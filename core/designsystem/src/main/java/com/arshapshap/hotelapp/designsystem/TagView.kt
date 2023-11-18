package com.arshapshap.hotelapp.designsystem

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isGone

class TagView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        View.inflate(context, R.layout.item_tag, this)

        setDefaultValues()

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TagView)
        try {
            setValuesFromAttributes(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    private fun setDefaultValues() {
        gravity = Gravity.CENTER
        setBackgroundResource(R.drawable.shape_tag)

        val paddingHorizontal = resources.getDimensionPixelOffset(R.dimen.tag_view_padding_horizontal)
        val paddingVertical = resources.getDimensionPixelOffset(R.dimen.tag_view_padding_vertical)
        setPadding(
            paddingHorizontal,
            paddingVertical,
            paddingHorizontal,
            paddingVertical
        )
    }

    private fun setValuesFromAttributes(typedArray: TypedArray) {
        val drawableStartId = typedArray.getResourceId(R.styleable.TagView_drawableStart, 0)
        findViewById<ImageView>(R.id.imageViewIconStart)?.setIconOrHide(drawableStartId)

        val drawableEndId = typedArray.getResourceId(R.styleable.TagView_drawableEnd, 0)
        findViewById<ImageView>(R.id.imageViewIconEnd)?.setIconOrHide(drawableEndId)

        val text = typedArray.getString(R.styleable.TagView_text)
        val textColor = typedArray.getColor(R.styleable.TagView_textColor, 0)
        findViewById<TextView>(R.id.textView)?.let {
            it.text = text
            it.setTextColor(textColor)
        }

        val shapeColor = typedArray.getColor(R.styleable.TagView_shapeColor, 0)
        background.setTint(shapeColor)
    }

    private fun ImageView.setIconOrHide(@DrawableRes drawableId: Int) {
        isGone = drawableId == 0
        setImageResource(drawableId)
    }
}