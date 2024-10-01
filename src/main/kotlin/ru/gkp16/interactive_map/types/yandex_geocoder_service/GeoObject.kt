package ru.gkp16.interactive_map.types.yandex_geocoder_service

import com.google.gson.annotations.SerializedName


data class GeoObject(
    @SerializedName("metaDataProperty") var metaDataProperty: MetaDataProperty? = MetaDataProperty(),
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("boundedBy") var boundedBy: BoundedBy? = BoundedBy(),
    @SerializedName("uri") var uri: String? = null,
    @SerializedName("Point") var Point: Point? = Point()
)