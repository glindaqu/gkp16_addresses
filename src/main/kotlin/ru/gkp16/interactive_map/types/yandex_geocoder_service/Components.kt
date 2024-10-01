package ru.gkp16.interactive_map.types.yandex_geocoder_service

import com.google.gson.annotations.SerializedName


data class Components(
    @SerializedName("kind") var kind: String? = null,
    @SerializedName("name") var name: String? = null
)