package com.trevorwiebe.caldav.presentation.calendarList.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trevorwiebe.caldav.data.model.Calendar
import com.trevorwiebe.caldav.presentation.ui.theme.generateOnColor

@Composable
fun CalendarView(
    calendar: Calendar
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = getColor(calendar.color),
        )
    ) {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp))
        Text(
            text = "Title:",
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
            fontWeight = FontWeight.Bold,
            color = getColor(calendar.color).generateOnColor(),
            fontSize = 18.sp
        )
        Text(
            text = calendar.title,
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
            color = getColor(calendar.color).generateOnColor()
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp))
        Text(
            text = "Description:",
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = getColor(calendar.color).generateOnColor()
        )
        Text(
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
            text = calendar.description,
            color = getColor(calendar.color).generateOnColor()
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp))
        Text(
            text = "TimeZone:",
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = getColor(calendar.color).generateOnColor()
        )
        Text(
            text = calendar.timeZone,
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
            color = getColor(calendar.color).generateOnColor()
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp))
        Text(
            text = "Supported Components:",
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = getColor(calendar.color).generateOnColor()
        )
        Text(
            text = calendar.supportedComponentSet.toString(),
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
            color = getColor(calendar.color).generateOnColor()
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp))
        Text(
            text = "Sync Token",
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = getColor(calendar.color).generateOnColor()
        )
        Text(
            text = calendar.syncToken,
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
            color = getColor(calendar.color).generateOnColor()
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp))
        Text(
            text = "Number of events",
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = getColor(calendar.color).generateOnColor()
        )
        Text(
            text = calendar.numberOfEvents.toString(),
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
            color = getColor(calendar.color).generateOnColor()
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp))
    }
}

fun getColor(colorString: String): Color {
    return Color(android.graphics.Color.parseColor(colorString))
}
