package ru.gkp16.interactive_map.types.yandex_geocoder_service

import com.google.gson.annotations.SerializedName


data class Locality(
    @SerializedName("LocalityName") var LocalityName: String? = null,
    @SerializedName("Thoroughfare") var Thoroughfare: Thoroughfare? = Thoroughfare()
)