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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trevorwiebe.caldav.presentation.CalDavEvents
import com.trevorwiebe.caldav.presentation.MainActivityViewModel

@Composable
fun AddCalendar(
    viewModel: MainActivityViewModel,
    saveCalendar: () -> Unit
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
        Text(text = "Enter your CalDav credentials to add a calendar.")
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
        CredentialText(value = "Username")
        PlainTextView(
            value = viewModel.state.username,
            placeHolder = "john.smith",
            onValueChange = { viewModel.onEvent(CalDavEvents.OnUsernameChange(it)) }
        )

        CredentialText(value = "Password")
        CredentialTextView(
            value = viewModel.state.password,
            onValueChange = { viewModel.onEvent(CalDavEvents.OnPasswordChange(it)) }
        )

        CredentialText(value = "CalDav URL")
        PlainTextView(
            value = viewModel.state.url,
            placeHolder = "https://calendar.example.com/dav.php/",
            onValueChange = { viewModel.onEvent(CalDavEvents.OnURLChange(it)) }
        )

        Button(
            onClick = saveCalendar,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 36.dp, 0.dp, 16.dp)
                .clip(RoundedCornerShape(50.dp))
        ) {
            Text(text = "Submit", modifier = Modifier.padding(0.dp, 12.dp, 0.dp, 12.dp))
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
    }
}