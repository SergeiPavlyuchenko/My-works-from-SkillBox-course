package com.skillbox.lists_2

import android.os.Parcelable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
data class StateGameGenres(val gameGenres: @RawValue List<GameGenre>): Parcelable

@Parcelize
data class StateLayoutManager(val layoutManager: @RawValue RecyclerView.LayoutManager): Parcelable

