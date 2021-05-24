package com.skillbox.github.ui.repository_list

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerItemsWrapper<T>(
    val owner: T
)
