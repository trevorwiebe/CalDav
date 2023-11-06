package com.trevorwiebe.caldav.presentation.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


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
    val formatter = DateTimeFormatter.ofPattern("h:mm:a")
    return this.format(formatter)
}

fun LocalDate.toFriendlyDayOfWeekName(): String {
    return this.dayOfWeek.toString()
}