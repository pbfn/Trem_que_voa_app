package com.br.ioasys.tremquevoa.extensions

import java.text.NumberFormat

fun Double.toMoney(): String {
    val numberFormat: NumberFormat = NumberFormat.getCurrencyInstance()
    numberFormat.minimumFractionDigits = 2
    numberFormat.maximumFractionDigits = 2
    return numberFormat.format(this)
}