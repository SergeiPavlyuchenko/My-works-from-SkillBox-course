package com.skillbox.github.ui.repository_list


import com.squareup.moshi.ToJson


class PatchDataCustomJsonAdapter {

    @ToJson
    fun toJson(data: DataForPatch): String {
        return "\"location\" : \"$data.location\""
    }

}