package com.app7k.lounge.presentation.screens.tabs

import com.app7k.lounge.R

enum class Tabs(
    val titleRes: Int,
    val iconRes: Int
) {

    ABOUT(R.string.tabs_about, R.drawable.about),
    RESERVATION(R.string.tabs_reservation, R.drawable.reservation),
    MENU(R.string.tabs_menu, R.drawable.menu),
    CONTACT(R.string.tabs_contact, R.drawable.contact),
    PROMO(R.string.tabs_promo, R.drawable.promo),

}