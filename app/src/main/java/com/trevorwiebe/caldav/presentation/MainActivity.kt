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
import com.trevorwiebe.caldav.presentation.calendar.CalendarScreen
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

                if(viewModel.state.authUserModel != null){
                    initialScreen = CalDavScreens.Calendar
                }

                Scaffold{ innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = initialScreen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = CalDavScreens.Calendar){
                            CalendarScreen(
                                viewModel = viewModel,
                                navToAddCal = {navController.navigate(CalDavScreens.AddCalendar)}
                            )
                        }
                        composable(route = CalDavScreens.AddCalendar){
                            AddCalendarScreen(
                                viewModel = viewModel,
                                saveCalendar = {
                                    viewModel.onEvent(CalDavEvents.OnAddCal)
                                    navController.navigate(CalDavScreens.Calendar)
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
                    }
                }
            }
        }
    }
}
