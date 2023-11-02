package com.trevorwiebe.caldav.presentation.calendarEvents.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trevorwiebe.caldav.presentation.ui.theme.generateOnColor

@Composable
fun TodayBlockDateLabel(
    date: String,
    color: Color
) {
    Text(
        modifier = Modifier
            .width(26.dp)
            .padding(1.dp)
            .background(color, RoundedCornerShape(6.dp)),
        text = date,
        textAlign = TextAlign.Center,
        color = color.generateOnColor(),
        fontSize = 14.sp
    )
}