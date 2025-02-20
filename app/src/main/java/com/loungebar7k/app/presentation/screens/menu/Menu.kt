package com.loungebar7k.app.presentation.screens.menu

import com.loungebar7k.app.R

enum class Menu(
    val named: String,
    val ingrRes: Int,
    val price: String
) {

    GoldenSunset(
        named = "Golden Sunset",
        ingrRes = R.string.golden_sunset_ingr,
        price = "18$"
    ),

    BlueLagoon(
        named = "Blue Lagoon",
        ingrRes = R.string.blue_lagoon_ingr,
        price = "16$"
    ),

    TropicalBreeze(
        named = "Tropical Breeze",
        ingrRes = R.string.tropical_breeze_ingr,
        price = "15$"
    ),

    CitrusMors(
        named = "Citrus Mors",
        ingrRes = R.string.citrus_mors_ingr,
        price = "7$"
    ),

    GourmetBurger(
        named = "Gourmet Burger",
        ingrRes = R.string.gourmet_burger_ingr,
        price = "19$"
    ),

    BruschettaSelection(
        named = "Bruschetta Selection",
        ingrRes = R.string.bruschetta_selection_ingr,
        price = "18$"
    ),

}