package ru.gkp16.interactive_map.types.yandex_geocoder_service

import com.google.gson.annotations.SerializedName


data class GeocoderResponseMetaData(
    @SerializedName("request") var request: String? = null,
    @SerializedName("results") var results: String? = null,
    @SerializedName("found") var found: String? = null
)