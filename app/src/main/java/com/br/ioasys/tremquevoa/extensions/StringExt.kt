package com.br.ioasys.tremquevoa.extensions

import android.text.Editable

fun Editable?.toInt(): Int {
    return this.toString().toInt()
}