package com.skillbox.github.ui.current_user

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class RemoteUser(
    @Json(name = "login")
    val login: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "location")
    var location: String?,
    @Json(name = "email")
    val email:String?,
    @Json(name = "avatar_url")
    val avatarUrl: String
): Parcelable {
}