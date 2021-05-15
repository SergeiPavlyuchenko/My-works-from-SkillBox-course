package com.example.moshi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Score(
    @Json(name = "Source")
    val score: String,
    @Json(name = "Value")
    val value: String
)