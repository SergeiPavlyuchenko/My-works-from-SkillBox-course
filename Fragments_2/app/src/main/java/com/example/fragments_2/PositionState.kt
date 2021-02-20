package com.example.fragments_2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PositionState(val positions: List<ArticleTag>) : Parcelable {

}