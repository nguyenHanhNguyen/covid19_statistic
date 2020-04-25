package com.n2n.covid19.extension

import java.text.SimpleDateFormat
import java.util.*

fun convertUtcFormat(utcDate: String): String {
    var displayTime = ""
    val utcDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    utcDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = utcDateFormat.parse(utcDate)

    val displayTimeFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US)
    date?.let {
        displayTime = displayTimeFormat.format(it)
    }
    return displayTime
}