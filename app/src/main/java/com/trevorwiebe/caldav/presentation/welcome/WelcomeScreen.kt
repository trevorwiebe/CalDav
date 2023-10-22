package com.trevorwiebe.caldav.presentation.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.trevorwiebe.caldav.R
import com.trevorwiebe.caldav.presentation.CalDavScreens

@Composable
fun WelcomeScreen(
    navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                modifier = Modifier.padding(24.dp),
                text = "Welcome to",
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 52.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(24.dp)
            )
            Text(
                modifier = Modifier.padding(24.dp),
                text = "- A simple CalDav client -",
                color = MaterialTheme.colorScheme.onPrimary
            )
            Icon(
                modifier = Modifier
                    .padding(24.dp)
                    .size(72.dp),
                painter = painterResource(
                    id = R.drawable.baseline_calendar_month_24
                ),
                contentDescription = "Cream",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        Button(
            onClick = {
                navController.navigate(CalDavScreens.AddCalendar)
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(32.dp)
                .align(Alignment.BottomEnd),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "Connect to Server",
                color = MaterialTheme.colorScheme.primary
            )
            Icon(
                painter = painterResource(
                    id = R.drawable.baseline_navigate_next_24
                ),
                contentDescription = "Connect to Server",
                tint = MaterialTheme.colorScheme.primary
            )
        }

    }

}