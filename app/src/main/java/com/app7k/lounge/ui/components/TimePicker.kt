package com.app7k.lounge.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app7k.lounge.ui.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(
    state: TimePickerState,
    onDismissRequest: () -> Unit,
    onClickConfirm: () -> Unit,
) {
    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    onClickConfirm()
                    onDismissRequest()
                },
            ) {
                AppText("OK", color = Colors.RED)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) { AppText("Cancel", color = Color.Black) }
        }
    ) {
        androidx.compose.material3.TimePicker(
            state = state,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 20.dp)
        )
    }
}