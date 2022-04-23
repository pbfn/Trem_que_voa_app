package com.br.ioasys.tremquevoa.extensions

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.br.ioasys.tremquevoa.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun TextInputEditText.ChangeIcon(icon: Drawable, eventClick: () -> Unit) {
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(
        null,
        null,
        icon,
        null
    )
    this.setOnTouchListener { _, _ ->
        eventClick()
        true
    }
}
        