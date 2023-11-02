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
import com.trevorwiebe.caldav.domain.model.EventModel
import com.trevorwiebe.caldav.presentation.ui.theme.generateOnColorFromBaseColorString
import com.trevorwiebe.caldav.presentation.ui.theme.getColorFromString
import com.trevorwiebe.caldav.presentation.ui.theme.toFriendlyTime

@Composable
fun DayListEventItem(
    eventModel: EventModel
) {

    val eventName = eventModel.summary.toString()

    val eventDate = eventModel.startDate?.toFriendlyTime() +
            " - " +
            eventModel.endDate?.toFriendlyTime()

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp, 8.dp, 8.dp, 0.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(eventModel.color.getColorFromString())
    ) {
        Text(
            text = eventName,
            color = eventModel.color.generateOnColorFromBaseColorString(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 8.dp, 8.dp, 2.dp),
            maxLines = 1
        )
        Text(
            text = eventDate,
            fontSize = 14.sp,
            color = eventModel.color.generateOnColorFromBaseColorString(),
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp)
        )
    }
}