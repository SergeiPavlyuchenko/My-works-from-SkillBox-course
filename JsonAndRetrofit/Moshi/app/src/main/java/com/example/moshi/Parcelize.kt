package com.example.moshi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class CurrentItemState(
    val currentMovieList: @RawValue List<RemoteMovie>,
    val adapterPosition: Int
): Parcelable

@Parcelize
data class NewScore(
    val score: String,
    val value: String
): Parcelable
