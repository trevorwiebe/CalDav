package com.trevorwiebe.caldav.presentation.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter


fun Color.generateOnColor(): Color {
    return if (luminance() > 0.5f) {
        Color.Black.copy(alpha = .8f)
    } else {
        Color.White
    }
}

fun String.getColorFromString(): Color {
    return Color(android.graphics.Color.parseColor(this))
}

fun String.generateOnColorFromBaseColorString(): Color {
    val baseColor = Color(android.graphics.Color.parseColor(this))
    return if (baseColor.luminance() > 0.5f) {
        Color.Black.copy(alpha = .8f)
    } else {
        Color.White
    }
}

fun LocalDateTime.toFriendlyTime(): String {
    val dtf: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
    val dtfOut = DateTimeFormat.forPattern("h:mm:a")
    val jodaTime: DateTime = dtf.parseDateTime(this.toString())
    return dtfOut.print(jodaTime)
}

fun LocalDate.toFriendlyDayOfWeekName(): String {
    val dtf: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")
    val dtfOut = DateTimeFormat.forPattern("EEE")
    val jodaTime: DateTime = dtf.parseDateTime(this.toString())
    return dtfOut.print(jodaTime)
}