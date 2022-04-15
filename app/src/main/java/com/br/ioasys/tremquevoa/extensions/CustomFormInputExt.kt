package com.br.ioasys.tremquevoa.extensions

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.text.Editable
import android.view.MotionEvent
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.presentation.ui.custom_views.CustomFormInput

fun CustomFormInput.ChangeBackground(error: Boolean, msg: String?) {
    if (error) {
        this.apply {
            inputLayout.helperText = msg
            inputLayout.isErrorEnabled = error
            inputLayout.boxStrokeColor = ContextCompat.getColor(context, R.color.error)
            inputLayout.setErrorIconDrawable(R.drawable.ic_error)
            inputLayout.hintTextColor =
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.error))

        }

    } else {
        this.apply {
            inputLayout.isErrorEnabled = error
            inputLayout.boxStrokeColor = ContextCompat.getColor(context, R.color.purple_500)
            inputLayout.helperText = ""
            inputLayout.hintTextColor =
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.purple_500))
        }
    }
}

fun CustomFormInput.ChangeIcon(icon: Drawable, eventClick: () -> Unit) {
    input.setCompoundDrawablesRelativeWithIntrinsicBounds(
        null,
        null,
        icon,
        null
    )
    input.setOnTouchListener { _, _ ->
        eventClick()
        true
    }

}