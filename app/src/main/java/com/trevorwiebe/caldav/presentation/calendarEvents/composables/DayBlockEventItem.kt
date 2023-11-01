package com.trevorwiebe.caldav.presentation.calendarEvents.composables

import androidx.compose.foundation.background
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

@Composable
fun DayBlockEventItem(
    eventModel: EventModel
) {

    Text(
        text = eventModel.summary ?: "",
        color = eventModel.color.generateOnColorFromBaseColorString(),
        fontSize = 10.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.5.dp, .5.dp, 1.5.dp, .5.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(eventModel.color.getColorFromString())
            .padding(2.dp, 0.dp, 2.dp, 0.dp),
        maxLines = 1
    )
}