package ru.gkp16.interactive_map.services.map_service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.gkp16.interactive_map.types.map_service.Address

interface IMapService {
    @GET("get-addresses.php")
    fun getAddresses(): Call<List<Address>>

    @GET("exists.php")
    fun isAddressExists(
        @Query("street") street: String,
        @Query("housenumber") houseNumber: String
    ): Call<Boolean>

    @GET("upd.php")
    fun updateAddress(
        @Query("street") street: String,
        @Query("housenumber") houseNumber: String,
        @Query("flatcount") flatCount: String?,
        @Query("medicaldivision") medicalDivision: String?,
        @Query("region") region: String?
    ): Call<Void>

    @GET("add.php")
    fun insertAddress(
        @Query("prefix") prefix: String,
        @Query("street") street: String,
        @Query("medicaldivision") medicalDivision: String,
        @Query("region") region: String,
        @Query("houseNumber") houseNumber: String,
        @Query("flatcount") flatCount: String,
        @Query("longitude") longitude: String,
        @Query("latitude") latitude: String
    ): Call<Void>
}