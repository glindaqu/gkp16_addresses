package ru.gkp16.interactive_map.types.yandex_geocoder_service

import com.google.gson.annotations.SerializedName


data class GeocoderMetaData(
    @SerializedName("precision") var precision: String? = null,
    @SerializedName("text") var text: String? = null,
    @SerializedName("kind") var kind: String? = null,
    @SerializedName("Address") var Address: Address? = Address(),
    @SerializedName("AddressDetails") var AddressDetails: AddressDetails? = AddressDetails()
)