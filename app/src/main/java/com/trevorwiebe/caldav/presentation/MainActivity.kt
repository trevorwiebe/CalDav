package com.trevorwiebe.caldav.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trevorwiebe.caldav.presentation.calendar.Calendar
import com.trevorwiebe.caldav.presentation.add_cal.AddCalendar
import com.trevorwiebe.caldav.presentation.ui.theme.CalDavTheme
import com.trevorwiebe.caldav.presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalDavTheme {
                val navController = rememberNavController()
                val viewModel: MainActivityViewModel by viewModels()

                Scaffold{ innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = CalDavScreens.Welcome,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = CalDavScreens.Calendar){
                            Calendar(
                                viewModel = viewModel,
                                navToAddCal = {navController.navigate(CalDavScreens.AddCalendar)}
                            )
                        }
                        composable(route = CalDavScreens.AddCalendar){
                            AddCalendar(
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
