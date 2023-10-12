package com.trevorwiebe.caldav.presentation.calendar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.trevorwiebe.caldav.data.model.Event

@Composable
fun EventView(
    event: Event
) {

    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Text(text = "ID: ")
            Text(text = event.id ?: "")
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))
        Row(modifier = Modifier.padding(4.dp)) {
            Text(text = "LINK: ")
            Text(text = event.url ?: "")
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))
        Row(modifier = Modifier.padding(4.dp)) {
            Text(text = "STATUS: ")
            Text(text = event.status ?: "")
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))
        Row(modifier = Modifier.padding(4.dp)) {
            Text(text = "CALENDAR DATA: ")
            Text(text = event.calendarData ?: "")
        }
    }

}