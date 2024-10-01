package ru.gkp16.interactive_map.types.yandex_geocoder_service

import com.google.gson.annotations.SerializedName


data class GeoObjectCollection(
    @SerializedName("metaDataProperty") var metaDataProperty: MetaDataProperty? = MetaDataProperty(),
    @SerializedName("featureMember") var featureMember: ArrayList<FeatureMember> = arrayListOf()
)