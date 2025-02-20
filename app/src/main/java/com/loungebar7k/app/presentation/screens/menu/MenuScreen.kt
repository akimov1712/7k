package com.loungebar7k.app.presentation.screens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.loungebar7k.app.R
import com.loungebar7k.app.ui.components.AppText
import com.loungebar7k.app.ui.theme.Colors

object MenuScreen:Screen {

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
                text = stringResource(R.string.menu),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            Spacer(Modifier.height(40.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ){
                Menu.entries.forEach {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ){
                        Row{
                            AppText(
                                modifier = Modifier.weight(1f),
                                text = it.named,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                            AppText(
                                text = it.price,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                        }
                        Box(Modifier.fillMaxWidth().height(1.5.dp).background(Color.White))
                        AppText(
                            text = stringResource(it.ingrRes),
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }

}