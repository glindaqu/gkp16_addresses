package ru.gkp16.interactive_map.types.yandex_geocoder_service

import com.google.gson.annotations.SerializedName


data class Country(
    @SerializedName("AddressLine") var AddressLine: String? = null,
    @SerializedName("CountryNameCode") var CountryNameCode: String? = null,
    @SerializedName("CountryName") var CountryName: String? = null,
    @SerializedName("AdministrativeArea") var AdministrativeArea: AdministrativeArea? = AdministrativeArea()
)