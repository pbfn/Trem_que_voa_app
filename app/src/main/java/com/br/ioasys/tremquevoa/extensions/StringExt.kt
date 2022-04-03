package com.br.ioasys.tremquevoa.extensions

import android.text.Editable

fun Editable?.toInt(): Int? {
    return if(this?.isEmpty() == true) {
        0
    }else {
        this.toString().toInt()
    }
}