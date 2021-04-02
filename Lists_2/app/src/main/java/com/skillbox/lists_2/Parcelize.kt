package com.skillbox.lists_2

import android.os.Parcelable
import android.widget.Button
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
data class StateGameGenres(val gameGenres: @RawValue List<GameGenre>): Parcelable

