package com.skillbox.github.ui.repository_list

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataForPatch (
    @Json(name = "location")
    var location: String
    )