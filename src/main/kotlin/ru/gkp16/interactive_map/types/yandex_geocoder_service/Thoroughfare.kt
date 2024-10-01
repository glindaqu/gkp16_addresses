package ru.gkp16.interactive_map.types.yandex_geocoder_service

import com.google.gson.annotations.SerializedName


data class Thoroughfare(
    @SerializedName("ThoroughfareName") var ThoroughfareName: String? = null,
    @SerializedName("Premise") var Premise: Premise? = Premise()
)