package ru.gkp16.interactive_map.types.map_service

data class Address(
    var id: String? = null,
    var Street: String? = null,
    var Prefix: String? = null,
    var MedicalDivision: String? = null,
    var Region: String? = null,
    var HouseNumber: String? = null,
    var FlatCount: String? = null,
    var Longitude: String? = null,
    var Latitude: String? = null,
) {
    override fun toString(): String {
        return "id = $id, " +
                "street = $Street, " +
                "prefix = $Prefix, " +
                "md = $MedicalDivision, " +
                "reg = $Region, " +
                "house = $HouseNumber, " +
                "flats = $FlatCount, " +
                "lng = $Longitude, " +
                "lat = $Latitude"
    }
}
