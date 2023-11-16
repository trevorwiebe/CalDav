package com.trevorwiebe.caldav.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trevorwiebe.caldav.presentation.add_cal.AddCalendarScreen
import com.trevorwiebe.caldav.presentation.calendarEvents.CalendarEventScreen
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

                Scaffold{ innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = CalDavScreens.CalendarEvents,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = CalDavScreens.AddCalendar){
                            AddCalendarScreen(navController = navController)
                        }
                        composable(route = CalDavScreens.Welcome){
                            WelcomeScreen(navController = navController)
                        }
                        composable(route = CalDavScreens.CalendarEvents){
                            CalendarEventScreen(
                                navigateToWelcome = {
                                    navController.navigate(CalDavScreens.Welcome)
                                },
                                navigateToAddCalendar = {
                                    navController.navigate(CalDavScreens.AddCalendar)
                                },
                                navigateToUsers = {
                                    navController.navigate(CalDavScreens.EditUser)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
