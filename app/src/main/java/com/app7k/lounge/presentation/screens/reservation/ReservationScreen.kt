package com.app7k.lounge.presentation.screens.reservation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.app7k.lounge.R
import com.app7k.lounge.ui.components.AppButton
import com.app7k.lounge.ui.components.AppText
import com.app7k.lounge.ui.components.AppTextField
import com.app7k.lounge.ui.components.DatePicker
import com.app7k.lounge.ui.components.TimePicker
import com.app7k.lounge.ui.theme.Colors
import com.app7k.lounge.utills.formatTime
import com.app7k.lounge.utills.toFormattedDate

object ReservationScreen: Tab {

    override val options: TabOptions
        @Composable get() {
            val title = stringResource(R.string.tabs_reservation)
            val icon = painterResource(R.drawable.reservation)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val dateState = rememberDatePickerState()
        val timeState = rememberTimePickerState()
        var onOpenDatePicker by rememberSaveable { mutableStateOf(false) }
        var onOpenTimePicker by rememberSaveable { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Colors.RED_TO_RED_BLACK)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 30.dp, vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var reservationList by remember { mutableStateOf<List<ReservationEntity>>(emptyList()) }

            var name by rememberSaveable { mutableStateOf("") }
            var phone by rememberSaveable { mutableStateOf("") }
            var date by rememberSaveable { mutableStateOf("") }
            var time by rememberSaveable { mutableStateOf("") }

            if (onOpenDatePicker) {
                DatePicker(
                    state = dateState,
                    onDismissRequest = { onOpenDatePicker = false },
                    onClickConfirm = {
                        date = dateState.selectedDateMillis?.toFormattedDate() ?: ""
                    }
                )
            }

            if (onOpenTimePicker) {
                TimePicker(
                    state = timeState,
                    onDismissRequest = { onOpenTimePicker = false },
                    onClickConfirm = {
                        time = formatTime(timeState.hour, timeState.minute)
                    }
                )
            }

            AppText(
                text = stringResource(R.string.table_reservation),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            Spacer(Modifier.height(50.dp))
            AppText(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.table) + " ${reservationList.size + 1}",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
            Spacer(Modifier.height(10.dp))
            Fields(
                name = name,
                phone = phone,
                date = date,
                time = time,
                changeName = {name = it},
                changePhone = {phone = it},
                changeOpenDateDialog = {onOpenDatePicker = it},
                changeOpenTimeDialog = {onOpenTimePicker = it}
            )
            Spacer(Modifier.height(20.dp))
            AppButton(
                text = stringResource(R.string.reserve),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(horizontal = 20.dp),
                enable = !listOf(name, phone, date, time).contains("")
            ) {
                val reservation = ReservationEntity(
                    name = name,
                    phoneNumber = phone,
                    date = date,
                    time = time
                )
                name = ""
                phone = ""
                date = ""
                time = ""
                reservationList = reservationList.toMutableList().apply { add(reservation) }
            }
            Spacer(Modifier.height(20.dp))
            AppText(
                text = stringResource(R.string.reservation_list),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
            Spacer(Modifier.height(10.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                reservationList.forEachIndexed { index, item ->
                    ReservationItem(index, item)
                }
            }
        }
    }

    @Composable
    private fun ReservationItem(
        index: Int,
        item: ReservationEntity
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            AppText(
                text = stringResource(R.string.table) + " ${index + 1}",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
            AppText(
                text = stringResource(R.string.name) + ": ${item.name}",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
            AppText(
                text = stringResource(R.string.phone) + ": ${item.phoneNumber}",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
            AppText(
                text = stringResource(R.string.date) + ": ${item.date} ${item.time}",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
    }

    @Composable
    private fun Fields(
        name: String,
        phone: String,
        date: String,
        time: String,
        changeName: (String) -> Unit,
        changePhone: (String) -> Unit,
        changeOpenDateDialog: (Boolean) -> Unit,
        changeOpenTimeDialog: (Boolean) -> Unit,
    ) {
        AppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            placeholder = stringResource(R.string.name),
            onValueChange = { changeName(it) }
        )
        Spacer(Modifier.height(10.dp))
        AppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = phone,
            placeholder = stringResource(R.string.phone_number),
            onValueChange = { if (it.length <= 13) changePhone(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            AppText(
                text = stringResource(R.string.date),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
            AppTextField(
                modifier = Modifier
                    .weight(1f)
                    .clickable { changeOpenDateDialog(true) },
                value = date,
                placeholder = stringResource(R.string.choose),
                onValueChange = {},
                textAlign = TextAlign.Center,
                enabled = false
            )
        }
        Spacer(Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            AppText(
                text = stringResource(R.string.time),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
            AppTextField(
                modifier = Modifier
                    .weight(1f)
                    .clickable { changeOpenTimeDialog(true) },
                value = time,
                placeholder = stringResource(R.string.choose),
                onValueChange = {},
                textAlign = TextAlign.Center,
                enabled = false
            )
        }
    }
}

