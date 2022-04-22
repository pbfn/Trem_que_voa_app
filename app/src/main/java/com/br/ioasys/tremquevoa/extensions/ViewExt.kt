package com.br.ioasys.tremquevoa.extensions

import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.GONE
}

fun View.show(isShow: Boolean) {
    if(isShow) this.visible() else this.invisible()
}