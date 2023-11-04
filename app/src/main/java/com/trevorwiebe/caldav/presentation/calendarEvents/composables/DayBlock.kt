package com.trevorwiebe.caldav.presentation.calendarEvents.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trevorwiebe.caldav.presentation.calendarEvents.DayUi
import org.joda.time.LocalDate

@Composable
fun DayBlock(
    dayUi: DayUi
) {

    Column(
        modifier = Modifier
            .padding(2.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(Color.White)
            .height(96.dp)
    ) {
        if(dayUi.date == LocalDate.now()){
            TodayBlockDateLabel(
                date = dayUi.date.dayOfMonth.toString(),
                color = MaterialTheme.colorScheme.tertiary
            )
        }else{
            Text(
                modifier = Modifier.padding(2.dp, 0.dp, 2.dp, 0.dp),
                text = dayUi.date.dayOfMonth.toString(),
                fontSize = 14.sp
            )
        }

        val filteredEventList = remember(dayUi.eventList){
            dayUi.eventList.filter { it.summary != "" }
        }

        if(filteredEventList.size <= 4) {
            filteredEventList.forEach {
                DayBlockEventItem(
                    eventSummary = it.summary ?: "",
                    eventColor = it.color
                )
            }
        }else{
            filteredEventList.take(4).forEach{
                DayBlockEventItem(
                    eventSummary = it.summary ?: "",
                    eventColor = it.color
                )
            }
            MoreEventsEllipsis()
        }
    }

}