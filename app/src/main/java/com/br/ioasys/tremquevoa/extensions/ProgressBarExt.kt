package com.br.ioasys.tremquevoa.extensions

import android.view.View
import android.widget.ProgressBar


fun ProgressBar.show(show: Boolean) {
    if (show) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}