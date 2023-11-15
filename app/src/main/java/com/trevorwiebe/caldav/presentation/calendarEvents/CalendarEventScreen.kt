package com.trevorwiebe.caldav.presentation.calendarEvents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.trevorwiebe.caldav.R
import com.trevorwiebe.caldav.presentation.CalDavScreens
import com.trevorwiebe.caldav.presentation.calendarEvents.composables.DayBlock
import com.trevorwiebe.caldav.presentation.calendarEvents.composables.DayOfWeekText
import com.trevorwiebe.caldav.presentation.calendarEvents.composables.CalendarView
import com.trevorwiebe.caldav.presentation.calendarEvents.composables.DayList
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarEventScreen(
    viewModel: CalendarEventViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.state

    if(state.isAuthUserListNull){
        navController.navigate(CalDavScreens.Welcome)
    }

    val scaffoldState = rememberBottomSheetScaffoldState()
    val eventList = remember { state.dayUiList }
    val scrollToPosition = eventList.indexOfFirst {
        it.date == LocalDate.now()
    }
    val lazyGridState = rememberLazyGridState(
        initialFirstVisibleItemIndex = scrollToPosition
    )
    val lazyColumnState = rememberLazyListState(
        initialFirstVisibleItemIndex = scrollToPosition
    )
    val coroutineScope = rememberCoroutineScope()
    val bottomScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
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
                            lazyColumnState.animateScrollToItem(scrollToPosition)
                        }
                    }) {
                        Text(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(MaterialTheme.colorScheme.tertiary)
                                .padding(4.dp, 1.dp, 4.dp, 1.dp)
                                .width(24.dp),
                            text = LocalDate.now().dayOfMonth.toString(),
                            color = MaterialTheme.colorScheme.onTertiary,
                            textAlign = TextAlign.Center
                        )
                    }
                    IconButton(onClick = {viewModel.onEvent(CalendarEventUiEvents.ToggleViewState)}) {
                        Icon(
                            if (state.isGrid)
                                painterResource(id = R.drawable.baseline_calendar_view_day_24)
                            else
                                painterResource(id = R.drawable.baseline_calendar_month_24),
                            contentDescription = "switch calendar view"
                        )
                    }
                    IconButton(onClick = {
                        bottomScope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_edit_calendar_24),
                            contentDescription = "Calendar List"
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.app_name), fontSize = 25.sp)
                }
            )
        },
        sheetContent = {
            Surface(
                // Add this somewhere near the top of your layout, above any scrolling layouts
                modifier = Modifier.nestedScroll(rememberNestedScrollInteropConnection())
            ) {
                LazyColumn {
                    item{
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = "Available calendars",
                            fontSize = 20.sp
                        )
                    }
                    items(state.calList){
                        CalendarView(
                            calendarTitle = it.title,
                            calendarColor = it.color,
                            calendarVisibility = it.isVisible,
                            numberOfEvents = it.numberOfEvents.toString(),
                            toggleVisibility = {
                                viewModel.onEvent(
                                    CalendarEventUiEvents.ToggleCalendarVisibility(it.url)
                                )
                            }
                        )
                    }
                    item {
                        Button(
                            onClick = {
                                navController.navigate(CalDavScreens.AddCalendar)
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .padding(8.dp, 32.dp, 8.dp, 32.dp)
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = "Add New Calendar",
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.baseline_add_circle_outline_24
                                ),
                                contentDescription = "Add new calendar",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }
        },
        sheetPeekHeight = 0.dp,
        sheetShadowElevation = 16.dp
    ) {  innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {

            if(state.isGrid){

                Row(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
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
                    items(
                        count = eventList.size,
                        key = {eventList[it].date},
                        itemContent = { index ->
                            DayBlock(dayUi = eventList[index])
                        }
                    )
                }
            }else{
                LazyColumn(
                    state = lazyColumnState
                ){
                    items(
                        count = eventList.size,
                        key = {eventList[it].date},
                        itemContent = { index ->
                            DayList(dayUi = eventList[index])
                        }
                    )
                }
            }
        }
    }

}