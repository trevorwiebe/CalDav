package com.trevorwiebe.caldav.presentation.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

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