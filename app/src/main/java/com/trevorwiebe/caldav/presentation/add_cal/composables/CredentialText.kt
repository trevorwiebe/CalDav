package com.trevorwiebe.caldav.presentation.add_cal.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CredentialText(
    value: String
) {
    Text(
        text = value,
        modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onPrimary
    )
}