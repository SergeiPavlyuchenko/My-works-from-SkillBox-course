package com.skillbox.github.ui.current_user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteUser(
    @Json(name = "login")
    val login: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "location")
    val location: String,
    @Json(name = "email")
    val email:String,
    @Json(name = "avatar_url")
    val avatarUrl: String
) {
}