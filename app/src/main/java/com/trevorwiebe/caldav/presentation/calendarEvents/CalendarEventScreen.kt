package com.trevorwiebe.caldav.presentation.calendarEvents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.trevorwiebe.caldav.R
import com.trevorwiebe.caldav.presentation.CalDavScreens
import com.trevorwiebe.caldav.presentation.calendarEvents.composables.DayBlock
import com.trevorwiebe.caldav.presentation.calendarEvents.composables.DayOfWeekText
import kotlinx.coroutines.launch
import org.joda.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarEventScreen(
    viewModel: CalendarEventViewModel = hiltViewModel(),
    navController: NavController
) {

    if(viewModel.state.isAuthUserListNull){
        navController.navigate(CalDavScreens.Welcome)
    }

    val eventList = viewModel.state.calEventList
    val scrollToPosition = eventList.indexOfFirst {
        it.date == LocalDate.now()
    }
    val lazyGridState = rememberLazyGridState(
        initialFirstVisibleItemIndex = scrollToPosition
    )
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                ),
                actions = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            lazyGridState.animateScrollToItem(scrollToPosition)
                        }
                    }) {
                        Text(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(MaterialTheme.colorScheme.tertiary)
                                .padding(4.dp, 1.dp, 4.dp, 1.dp),
                            text = "25",
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate(CalDavScreens.CalendarList)
                    }) {
                        Icon(Icons.Filled.List, contentDescription = "Calendar List")
                    }
                    IconButton(onClick = {
                        navController.navigate(CalDavScreens.AddCalendar)
                    }) {
                        Icon(Icons.Filled.AddCircle, contentDescription = "Add Calendar")
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.app_name), fontSize = 25.sp)
                }
            )
        },
    ) {  innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {

            Row {
                DayOfWeekText(dayInitial = "S", modifier = Modifier.weight(1f))
                DayOfWeekText(dayInitial = "M", modifier = Modifier.weight(1f))
                DayOfWeekText(dayInitial = "T", modifier = Modifier.weight(1f))
                DayOfWeekText(dayInitial = "W", modifier = Modifier.weight(1f))
                DayOfWeekText(dayInitial = "T", modifier = Modifier.weight(1f))
                DayOfWeekText(dayInitial = "F", modifier = Modifier.weight(1f))
                DayOfWeekText(dayInitial = "S", modifier = Modifier.weight(1f))
            }

            LazyVerticalGrid(
                state = lazyGridState,
                columns = GridCells.Fixed(7)
            ){
                items(eventList){
                    DayBlock(dayUi = it)
                }
            }
        }
    }

}