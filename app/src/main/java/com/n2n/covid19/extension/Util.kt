package com.n2n.covid19.extension

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
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

fun TextView.setSpanText(spanText: String, text: String, spanStyle: StyleSpan, color: Int) {
    val sb = SpannableStringBuilder(text)
    val start = text.indexOf(spanText, ignoreCase = true)
    val end = start + spanText.length
    sb.setSpan(spanStyle, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    sb.setSpan(ForegroundColorSpan(context.getColor(color)), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    this.text = sb
}