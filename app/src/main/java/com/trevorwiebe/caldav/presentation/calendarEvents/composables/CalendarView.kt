package com.trevorwiebe.caldav.presentation.calendarEvents.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trevorwiebe.caldav.data.model.Calendar
import com.trevorwiebe.caldav.presentation.ui.theme.generateOnColorFromBaseColorString
import com.trevorwiebe.caldav.presentation.ui.theme.getColorFromString

@Composable
fun CalendarView(
    calendar: Calendar
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = calendar.color.getColorFromString(),
        )
    ) {
        Row {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Checkbox(checked = true, onCheckedChange = {})
            }
            Column(modifier = Modifier.weight(5f)){
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp))
                Text(
                    text = calendar.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
                    color = calendar.color.generateOnColorFromBaseColorString()
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                )
                Text(
                    modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
                    text = calendar.description,
                    color = calendar.color.generateOnColorFromBaseColorString()
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp))
                val eventString = "Total events: ${calendar.numberOfEvents.toString()}"
                Text(
                    text = eventString,
                    modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
                    color = calendar.color.generateOnColorFromBaseColorString()
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp))
            }
        }
    }
}
