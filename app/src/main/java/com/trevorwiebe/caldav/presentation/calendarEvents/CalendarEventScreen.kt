package com.trevorwiebe.caldav.presentation.calendarEvents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.trevorwiebe.caldav.R
import com.trevorwiebe.caldav.presentation.CalDavScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarEventScreen(
    viewModel: CalendarEventViewModel = hiltViewModel(),
    navController: NavController
) {

    if(viewModel.state.isAuthUserListNull){
        navController.navigate(CalDavScreens.Welcome)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(CalDavScreens.CalendarList)
                    }) {
                        Icon(Icons.Filled.DateRange, contentDescription = null)
                    }
                    IconButton(onClick = {
                        navController.navigate(CalDavScreens.AddCalendar)
                    }) {
                        Icon(Icons.Filled.AddCircle, contentDescription = null)
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.app_name), fontSize = 25.sp)
                }
            )
        },
    ) {  innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {

        }
    }

}