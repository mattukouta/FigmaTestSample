package com.kouta.figmapluginsample

import androidx.annotation.DrawableRes

data class Spot(
    @DrawableRes
    val image: Int,
    val name: String,
    val postCode: String,
    val address: String
)
