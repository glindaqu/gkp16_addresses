package ru.gkp16.interactive_map.services.map_service

import retrofit2.Call
import retrofit2.http.GET
import ru.gkp16.interactive_map.types.map_service.Address

interface IMapService {
    @GET("get-addresses.php")
    fun getAddresses(): Call<List<Address>>
}