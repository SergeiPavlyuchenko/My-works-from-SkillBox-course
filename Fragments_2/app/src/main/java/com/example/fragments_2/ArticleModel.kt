package com.example.fragments_2

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArticleModel(
        @StringRes val text: Int,
        @DrawableRes val image: Int
)