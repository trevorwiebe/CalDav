package com.trevorwiebe.caldav.presentation.add_cal

import androidx.compose.foundation.background
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trevorwiebe.caldav.presentation.add_cal.composables.CalendarsFoundDialog
import com.trevorwiebe.caldav.presentation.add_cal.composables.CredentialText
import com.trevorwiebe.caldav.presentation.add_cal.composables.CredentialTextView
import com.trevorwiebe.caldav.presentation.add_cal.composables.PlainTextView

@Composable
fun AddCalendarScreen(
    viewModel: AddCalendarViewModel = hiltViewModel(),
    navigateToCalendarEvent: () -> Unit
) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    showDialog = viewModel.state.authCalendarList.isNotEmpty()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
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
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
        Text(
            text = "Enter your CalDav credentials.",
            color = MaterialTheme.colorScheme.onPrimary
        )
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
                viewModel.onEvent(AddCalendarEvents.OnDiscoverCalendars)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 36.dp, 0.dp, 16.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "Submit",
                modifier = Modifier
                    .padding(0.dp, 12.dp, 0.dp, 12.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
    }

    if(showDialog){
        CalendarsFoundDialog(
            onDismissRequest = { viewModel.onEvent(AddCalendarEvents.OnCancelDialog) },
            nextButton = {
                viewModel.onEvent(AddCalendarEvents.OnSaveAuthCalendars)
                navigateToCalendarEvent()
            },
            calendarList = viewModel.state.authCalendarList
        )
    }
}