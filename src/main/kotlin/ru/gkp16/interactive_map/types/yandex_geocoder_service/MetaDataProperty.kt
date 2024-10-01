package ru.gkp16.interactive_map.types.yandex_geocoder_service

import com.google.gson.annotations.SerializedName


data class MetaDataProperty(
    @SerializedName("GeocoderMetaData") var GeocoderMetaData: GeocoderMetaData? = GeocoderMetaData()
)