package com.trevorwiebe.caldav.presentation.add_cal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.trevorwiebe.caldav.presentation.CalDavScreens
import com.trevorwiebe.caldav.presentation.add_cal.composables.CredentialText
import com.trevorwiebe.caldav.presentation.add_cal.composables.CredentialTextView
import com.trevorwiebe.caldav.presentation.add_cal.composables.PlainTextView

@Composable
fun AddCalendarScreen(
    viewModel: AddCalendarViewModel = hiltViewModel(),
    navController: NavController
) {

    Column(
        modifier = Modifier
            .padding(16.dp, 0.dp, 16.dp, 0.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(36.dp))
        Text(
            text = "Add Calendar",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
        Text(text = "Enter your users CalDav credentials.")
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
        CredentialText(value = "Username")
        PlainTextView(
            value = viewModel.state.username,
            placeHolder = "john.smith",
            onValueChange = { viewModel.onEvent(
                AddCalendarEvents.OnUsernameChange(it)
            ) }
        )

        CredentialText(value = "Password")
        CredentialTextView(
            value = viewModel.state.password,
            onValueChange = { viewModel.onEvent(
                AddCalendarEvents.OnPasswordChange(it)
            ) }
        )

        CredentialText(value = "CalDav URL")
        PlainTextView(
            value = viewModel.state.url,
            placeHolder = "https://calendar.example.com/dav.php/",
            onValueChange = { viewModel.onEvent(
                AddCalendarEvents.OnURLChange(it)
            ) }
        )

        Button(
            onClick = {
                navController.navigate(CalDavScreens.CalendarEvents)
                viewModel.onEvent(AddCalendarEvents.OnAddCal)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 36.dp, 0.dp, 16.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Submit", modifier = Modifier.padding(0.dp, 12.dp, 0.dp, 12.dp))
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
    }
}