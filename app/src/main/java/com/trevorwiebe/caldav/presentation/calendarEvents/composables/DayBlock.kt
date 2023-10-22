package com.trevorwiebe.caldav.presentation.calendarEvents.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.trevorwiebe.caldav.domain.model.EventModel
import com.trevorwiebe.caldav.presentation.calendarEvents.DayUi

@Composable
fun DayBlock(
    dayUi: DayUi
) {

    Column(
        modifier = Modifier
            .padding(2.dp)
            .clip(RoundedCornerShape(6.dp))
            .border(.5.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(6.dp))
            .background(Color.White)
            .height(96.dp)
    ) {
        Text(
            modifier = Modifier.padding(2.dp, 0.dp, 2.dp, 0.dp),
            text = "24"
        )
        DayItemEvent(
            eventModel = EventModel("", "", "", "", ""),
            color = MaterialTheme.colorScheme.primary
        )
        DayItemEvent(
            eventModel = EventModel("", "", "", "", ""),
            color = MaterialTheme.colorScheme.primary
        )
        DayItemEvent(
            eventModel = EventModel("", "", "", "", ""),
            color = MaterialTheme.colorScheme.primary
        )
        MoreEventsEllipsis()
    }

}