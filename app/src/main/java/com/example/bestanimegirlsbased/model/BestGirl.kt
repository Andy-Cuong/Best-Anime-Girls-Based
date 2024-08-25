package com.example.bestanimegirlsbased.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BestGirl(
    @StringRes val nameRes: Int,
    @StringRes val animeRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)