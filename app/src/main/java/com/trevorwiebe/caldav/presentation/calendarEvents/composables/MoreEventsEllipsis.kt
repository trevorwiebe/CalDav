package com.trevorwiebe.caldav.presentation.calendarEvents.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MoreEventsEllipsis() {

    Text(
        modifier = Modifier.padding(2.dp, 0.dp, 2.dp, 2.dp).fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = "---",
        fontWeight = FontWeight.Bold
    )
}