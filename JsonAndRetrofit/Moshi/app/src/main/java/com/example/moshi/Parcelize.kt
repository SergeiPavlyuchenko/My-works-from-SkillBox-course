package com.example.moshi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentItemState(
    val currentMovieList: List<RemoteMovie>,
    val adapterPosition: Int
): Parcelable {
}