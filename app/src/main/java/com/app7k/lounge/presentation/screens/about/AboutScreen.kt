package com.app7k.lounge.presentation.screens.about

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.app7k.lounge.R
import com.app7k.lounge.ui.components.AppText
import com.app7k.lounge.ui.theme.Colors

object AboutScreen: Tab {

    override val options: TabOptions
        @Composable get() {
            val title = stringResource(R.string.tabs_about)
            val icon = painterResource(R.drawable.about)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Colors.RED_TO_RED_BLACK)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 30.dp, vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppText(
                text = stringResource(R.string.lounge_information),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            Spacer(Modifier.height(40.dp))
            AppText(
                text = stringResource(R.string.contact),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
            Spacer(Modifier.height(2.dp))
            AppText(
                text = "Av. Amador Bueno da Veiga, 4017 - Vila Costa Melo, São Paulo - SP, 03653-000, Brazil",
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(40.dp))
            Image(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)),
                painter = painterResource(R.drawable.google_map),
                contentDescription = null
            )
            Spacer(Modifier.height(40.dp))
            AppText(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.working_hours),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
            Spacer(Modifier.height(2.dp))
            AppText(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.working_hours_descr),
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp
            )
        }
    }

}