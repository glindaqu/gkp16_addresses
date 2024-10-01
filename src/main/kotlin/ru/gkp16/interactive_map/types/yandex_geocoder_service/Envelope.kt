package ru.gkp16.interactive_map.types.yandex_geocoder_service

import com.google.gson.annotations.SerializedName


data class Envelope(
    @SerializedName("lowerCorner") var lowerCorner: String? = null,
    @SerializedName("upperCorner") var upperCorner: String? = null
)