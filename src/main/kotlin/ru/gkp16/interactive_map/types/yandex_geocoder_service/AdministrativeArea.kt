package ru.gkp16.interactive_map.types.yandex_geocoder_service

import com.google.gson.annotations.SerializedName


data class AdministrativeArea(
    @SerializedName("AdministrativeAreaName") var AdministrativeAreaName: String? = null,
    @SerializedName("SubAdministrativeArea") var SubAdministrativeArea: SubAdministrativeArea? = SubAdministrativeArea()
)