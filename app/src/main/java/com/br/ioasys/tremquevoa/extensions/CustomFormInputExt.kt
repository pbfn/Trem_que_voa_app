package com.br.ioasys.tremquevoa.extensions

import android.view.View
import androidx.core.content.ContextCompat
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.presentation.ui.custom_views.CustomFormInput

fun CustomFormInput.ChangeBackground(error: Boolean, msg: String?){
    if (error) {
        this.apply {
            msgError.visibility = View.VISIBLE
            input.background = ContextCompat.getDrawable(context, R.drawable.input_custom_error)
            input.setTextColor(ContextCompat.getColor(context, R.color.error))
            msgError.text = msg
        }

    } else {
        this.apply {
            msgError.visibility = View.INVISIBLE
            input.background =
                ContextCompat.getDrawable(context, R.drawable.input_custom_neutral)
            input.setTextColor(ContextCompat.getColor(context, R.color.neutral))
        }
    }
}