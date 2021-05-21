package com.example.moshi

import android.os.Parcelable
import kotlinx.android.parcel.RawValue

@kotlinx.parcelize.Parcelize
data class CurrentItemState(
    val currentMovieList: @kotlinx.parcelize.RawValue List<RemoteMovie>,
    val adapterPosition: Int
): Parcelable

@kotlinx.parcelize.Parcelize
data class NewScore(
    val score: String,
    val value: String
): Parcelable
