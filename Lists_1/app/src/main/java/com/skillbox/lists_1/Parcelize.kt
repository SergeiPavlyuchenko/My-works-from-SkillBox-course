package com.skillbox.lists_1

import android.os.Parcelable
import android.view.View
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
data class StateGameGenres(val gameGenres: @RawValue List<GameGenre>): Parcelable

