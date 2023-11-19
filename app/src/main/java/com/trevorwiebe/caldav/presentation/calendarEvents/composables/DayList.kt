package com.trevorwiebe.caldav.presentation.calendarEvents.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.trevorwiebe.caldav.presentation.calendarEvents.DayUi
import com.trevorwiebe.caldav.presentation.ui.theme.toFriendlyDayOfWeekName
import com.trevorwiebe.caldav.presentation.ui.theme.toFriendlyTime
import java.time.LocalDate

@Composable
fun DayList(
    dayUi: DayUi
) {

    val day = dayUi.date.toFriendlyDayOfWeekName() + " " + dayUi.date.dayOfMonth.toString()

    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 8.dp)
    ) {
        if(dayUi.date == LocalDate.now()){
            TodayListDateLabel(date = day)
        }else {
            Text(
                modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 0.dp),
                text = day,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        val filteredEventList = remember(dayUi.eventList){
            dayUi.eventList.filter { it.summary != "" }
        }
        
        filteredEventList.forEach{
            DayListEventItem(
                eventSummary = it.summary,
                eventColor = it.color,
                eventStartDate = it.startDate.toFriendlyTime(),
                eventEndDate = it.endDate.toFriendlyTime()
            )
        }
    }

}