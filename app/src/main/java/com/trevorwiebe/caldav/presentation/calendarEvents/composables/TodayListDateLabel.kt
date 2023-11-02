package com.trevorwiebe.caldav.presentation.calendarEvents.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.trevorwiebe.caldav.presentation.ui.theme.generateOnColor

@Composable
fun TodayListDateLabel(
    date: String,
    color: Color
) {
    Text(
        modifier = Modifier
            .wrapContentSize()
            .padding(3.dp)
            .background(color, RoundedCornerShape(8.dp))
            .padding(3.dp),
        text = date,
        textAlign = TextAlign.Center,
        color = color.generateOnColor()
    )
}