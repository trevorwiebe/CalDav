package com.trevorwiebe.caldav.presentation.calendarEvents.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trevorwiebe.caldav.presentation.ui.theme.generateOnColorFromBaseColorString
import com.trevorwiebe.caldav.presentation.ui.theme.getColorFromString

@Composable
fun DayListEventItem(
    eventSummary: String,
    eventColor: String,
    eventStartDate: String,
    eventEndDate: String
) {

    val eventDate = eventStartDate + " - " + eventEndDate

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp, 8.dp, 8.dp, 0.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(eventColor.getColorFromString())
    ) {
        Text(
            text = eventSummary,
            color = eventColor.generateOnColorFromBaseColorString(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 8.dp, 8.dp, 2.dp),
            maxLines = 1
        )
        Text(
            text = eventDate,
            fontSize = 14.sp,
            color = eventColor.generateOnColorFromBaseColorString(),
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp)
        )
    }
}