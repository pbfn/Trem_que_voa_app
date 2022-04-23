package com.br.ioasys.tremquevoa.extensions

import android.content.Context
import android.widget.Toast
import com.br.ioasys.tremquevoa.R

fun Toast.showComingSoon(context: Context) {
    Toast.makeText(context, context.getString(R.string.coming_soon), Toast.LENGTH_SHORT)
        .show()
}