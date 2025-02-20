package com.app7k.lounge.presentation.screens.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.app7k.lounge.R
import com.app7k.lounge.presentation.screens.about.AboutScreen
import com.app7k.lounge.presentation.screens.bonus.BonusScreen
import com.app7k.lounge.presentation.screens.contact.ContactScreen
import com.app7k.lounge.presentation.screens.menu.MenuScreen
import com.app7k.lounge.presentation.screens.reservation.ReservationScreen
import com.app7k.lounge.ui.components.AppText
import com.app7k.lounge.ui.theme.Colors

object TabsScreen: Screen {

    @Composable
    override fun Content() {
        TabNavigator(AboutScreen) {
            Scaffold(
                content = {
                    Box(Modifier.background(Colors.RED_TO_RED_BLACK).padding(it)){
                        CurrentTab()
                    }
                },
                containerColor = Color.Transparent,
                bottomBar = {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(12.dp)
                    ){
                        BottomBarItem(AboutScreen)
                        BottomBarItem(ReservationScreen)
                        BottomBarItem(MenuScreen)
                        BottomBarItem(ContactScreen)
                        BottomBarItem(BonusScreen)
                    }
                }
            )
        }
    }
}

@Composable
fun RowScope.BottomBarItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val color = if (tabNavigator.current == tab) Color(0xffFF3A3D) else Color.White
    Column(
        modifier = Modifier.weight(1f).clickable(
            remember { MutableInteractionSource() },
            null
        ) {
            tabNavigator.current = tab
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(
            modifier = Modifier.size(20.dp),
            painter = tab.options.icon ?: painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            tint = color
        )
        Spacer(Modifier.height(8.dp))
        AppText(
            text = tab.options.title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = color,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}