  package com.app7k.lounge.presentation.screens.bonus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.app7k.lounge.R
import com.app7k.lounge.presentation.screens.bonus.BonusFragment.QR
import com.app7k.lounge.presentation.screens.bonus.BonusFragment.SPIN
import com.app7k.lounge.presentation.screens.bonus.BonusFragment.WELCOME
import com.app7k.lounge.presentation.screens.bonus.BonusFragment.WIN
import com.app7k.lounge.presentation.screens.bonus.fragments.QrFragment
import com.app7k.lounge.presentation.screens.bonus.fragments.SpinFragment
import com.app7k.lounge.presentation.screens.bonus.fragments.WelcomeFragment
import com.app7k.lounge.presentation.screens.bonus.fragments.WinFragment
import com.app7k.lounge.ui.theme.Colors

object BonusScreen: Tab {

    override val options: TabOptions
        @Composable get() {
            val title = stringResource(R.string.tabs_promo)
            val icon = painterResource(R.drawable.promo)

            return remember {
                TabOptions(
                    index = 4u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Colors.RED_TO_RED_BLACK)
                .padding(horizontal = 30.dp, vertical = 50.dp),
        ) {
            var activeFragment by rememberSaveable { mutableStateOf(WELCOME) }
            var points by rememberSaveable { mutableStateOf(0) }

            when(activeFragment){
                WELCOME -> WelcomeFragment { activeFragment = SPIN }
                SPIN -> SpinFragment { points = it.toInt(); activeFragment = WIN }
                WIN -> WinFragment(points){ activeFragment = QR }
                QR -> QrFragment(points)
            }
        }
    }

}

