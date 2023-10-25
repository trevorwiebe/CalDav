package com.trevorwiebe.caldav.presentation.calendarEvents.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trevorwiebe.caldav.domain.model.EventModel
import com.trevorwiebe.caldav.presentation.ui.theme.generateOnColor

@Composable
fun DayItemEvent(
    eventModel: EventModel,
    color: Color
) {

        Text(
            text = eventModel.summary ?: "",
            color = color.generateOnColor(),
            fontSize = 10.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.5.dp, .5.dp, 1.5.dp, .5.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(color)
                .padding(2.dp, 0.dp, 2.dp, 0.dp),
            maxLines = 1
        )
}