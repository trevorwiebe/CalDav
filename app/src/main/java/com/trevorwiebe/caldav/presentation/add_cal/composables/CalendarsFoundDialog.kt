package com.trevorwiebe.caldav.presentation.add_cal.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.trevorwiebe.caldav.R
import com.trevorwiebe.caldav.domain.model.AuthCalendarModel

@Composable
fun CalendarsFoundDialog(
    onDismissRequest: () -> Unit,
    nextButton: () -> Unit,
    calendarList: List<AuthCalendarModel>
) {

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ){
            Column(
                modifier = Modifier.padding(18.dp),
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(24.dp)
                            .size(48.dp),
                        painter = painterResource(
                            id = R.drawable.baseline_calendar_month_24
                        ),
                        contentDescription = "Event Flow",
                        tint = MaterialTheme.colorScheme.onBackground
                    )

                    Text(
                        text = "Calendars Found",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.padding(8.dp))
                LazyColumn{
                    items(calendarList){
                        CalendarItem(authCalendarModel = it)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Cancel")
                    }
                    TextButton(
                        onClick = { nextButton() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Next")
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarItem(authCalendarModel: AuthCalendarModel) {
    Row(
        modifier = Modifier
            .padding(0.dp, 4.dp, 0.dp, 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp)
    ) {
        Text(
            text = authCalendarModel.calendarName,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}