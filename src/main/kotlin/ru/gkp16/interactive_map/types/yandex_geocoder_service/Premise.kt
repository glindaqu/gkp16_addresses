package ru.gkp16.interactive_map.types.yandex_geocoder_service

import com.google.gson.annotations.SerializedName


data class Premise(
    @SerializedName("PremiseNumber") var PremiseNumber: String? = null,
    @SerializedName("PostalCode") var PostalCode: PostalCode? = PostalCode()
)