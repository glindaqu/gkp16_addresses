package ru.gkp16.interactive_map.types.map_service

data class Address(
    val id: String,
    val Street: String,
    val Prefix: String,
    val MedicalDivision: String,
    val Region: String,
    val HouseNumber: String,
    val FlatCount: String,
    val Longitude: String,
    val Latitude: String,
)
