package com.app7k.lounge.ui.components

import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.app7k.lounge.ui.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    state: DatePickerState,
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
        androidx.compose.material3.DatePicker(
            state = state,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

