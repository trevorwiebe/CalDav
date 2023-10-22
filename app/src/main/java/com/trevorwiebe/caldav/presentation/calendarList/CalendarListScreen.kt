package com.trevorwiebe.caldav.presentation.calendarList

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.trevorwiebe.caldav.presentation.MainActivityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    viewModel: MainActivityViewModel,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                ),
                title = {
                    Text(text = "Saved Calendars", fontSize = 25.sp)
                }
            )
        },
    ) {  innerPadding ->

        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(viewModel.state.calList) { calendar ->
                CalendarView(calendar = calendar)
            }
        }
    }
}