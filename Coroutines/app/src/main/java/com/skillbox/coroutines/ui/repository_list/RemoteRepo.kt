package com.skillbox.coroutines.ui.repository_list

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class RemoteRepo(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "owner")
    val owner: RepoOwner,
    var isStarred: Boolean = true
): Parcelable