package com.trevorwiebe.caldav.presentation.calendarEvents.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trevorwiebe.caldav.R
import com.trevorwiebe.caldav.presentation.ui.theme.generateOnColorFromBaseColorString
import com.trevorwiebe.caldav.presentation.ui.theme.getColorFromString

@Composable
fun CalendarView(
    calendarTitle: String,
    calendarColor: String,
    calendarVisibility: Boolean,
    numberOfEvents: String,
    toggleVisibility: ()-> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = calendarColor.getColorFromString(),
        )
    ) {
        Row {
            Column(modifier = Modifier.weight(5f)){
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp))
                Text(
                    text = calendarTitle,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
                    color = calendarColor.generateOnColorFromBaseColorString()
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp))
                val eventString = "Total events: $numberOfEvents"
                Text(
                    text = eventString,
                    modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
                    color = calendarColor.generateOnColorFromBaseColorString()
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp))
            }
            IconButton(
                onClick = { toggleVisibility() },
                modifier = Modifier
                    .weight(1f)
                    .height(65.dp)
            ) {
                Icon(
                    painter = if(calendarVisibility)
                        painterResource(id = R.drawable.baseline_visibility_24)
                    else
                        painterResource(id = R.drawable.baseline_visibility_off_24),
                    contentDescription = "calendar visibility",
                    tint = calendarColor.generateOnColorFromBaseColorString()
                )
            }
        }
    }
}
