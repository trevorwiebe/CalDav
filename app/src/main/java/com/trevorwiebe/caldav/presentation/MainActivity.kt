package com.trevorwiebe.caldav.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trevorwiebe.caldav.presentation.add_cal.AddCalendarScreen
import com.trevorwiebe.caldav.presentation.calendarEvents.CalendarEventScreen
import com.trevorwiebe.caldav.presentation.calendarList.CalendarScreen
import com.trevorwiebe.caldav.presentation.ui.theme.CalDavTheme
import com.trevorwiebe.caldav.presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalDavTheme {
                val navController = rememberNavController()
                val viewModel: MainActivityViewModel by viewModels()

                var initialScreen = CalDavScreens.Welcome

                if(viewModel.state.authUserModelList.isNotEmpty()){
                    initialScreen = CalDavScreens.CalendarEvents
                }

                Scaffold{ innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = initialScreen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = CalDavScreens.CalendarList){
                            CalendarScreen(
                                viewModel = viewModel
                            )
                        }
                        composable(route = CalDavScreens.AddCalendar){
                            AddCalendarScreen(
                                viewModel = viewModel,
                                saveCalendar = {
                                    viewModel.onEvent(CalDavEvents.OnAddCal)
                                    navController.navigate(CalDavScreens.CalendarList)
                                }
                            )
                        }
                        composable(route = CalDavScreens.Welcome){
                            WelcomeScreen(
                                onNextClick = {
                                    navController.navigate(CalDavScreens.AddCalendar)
                                }
                            )
                        }
                        composable(route = CalDavScreens.CalendarEvents){
                            CalendarEventScreen(
                                navToCalList = {navController.navigate(CalDavScreens.CalendarList)},
                                navToAddCal = {navController.navigate(CalDavScreens.AddCalendar)}
                            )
                        }
                    }
                }
            }
        }
    }
}
