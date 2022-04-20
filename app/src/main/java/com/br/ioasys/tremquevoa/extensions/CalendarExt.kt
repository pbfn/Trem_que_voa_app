package com.br.ioasys.tremquevoa.extensions

import java.text.SimpleDateFormat
import java.util.*

const val FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'"
const val FORMAT_DATE_VIEW = "dd/MM/yyyy"
const val FORMAT_DATE_VIEW_SHORT = "dd 'de' MMMM"
const val FORMAT_HOUR = "HH:mm"

fun Calendar.toString(stringFormat: String): String {
    val simpleDateFormat = SimpleDateFormat(stringFormat)
    val dateStringFormated = simpleDateFormat.format(this.time)
    return dateStringFormated
}

fun Date.toString(stringFormat: String): String {
    val simpleDateFormat = SimpleDateFormat(stringFormat)
    val dateStringFormated = simpleDateFormat.format(this.time)
    return dateStringFormated
}



