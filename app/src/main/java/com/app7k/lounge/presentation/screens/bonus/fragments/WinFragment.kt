package com.app7k.lounge.presentation.screens.bonus.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app7k.lounge.R
import com.app7k.lounge.ui.components.AppButton
import com.app7k.lounge.ui.components.AppText

@Composable
fun WinFragment(points: Int, onNextFragment: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppText(
            text = stringResource(R.string.you_win),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Image(
            painter = painterResource(R.drawable.gift),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            AppText(
                text = points.toString(),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
            Spacer(Modifier.height(4.dp))
            AppText(
                text = stringResource(R.string.promo_points),
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp
            )
            Spacer(Modifier.height(50.dp))
            AppButton(
                text = stringResource(R.string.take),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
            ) {
                onNextFragment()
            }
        }
    }
}