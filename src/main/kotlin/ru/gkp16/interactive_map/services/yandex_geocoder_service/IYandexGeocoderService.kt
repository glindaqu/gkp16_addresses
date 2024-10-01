package ru.gkp16.interactive_map.services.yandex_geocoder_service

import ru.gkp16.interactive_map.types.yandex_geocoder_service.YandexGeocoderAPIAnswer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.gkp16.interactive_map.config.apikeys.YANDEX_GEOCODER_API_KEY

interface IYandexGeocoderService {
    @GET("")
    fun getData(
        @Query("geocode") query: String,
        @Query("apikey") apikey: String = YANDEX_GEOCODER_API_KEY,
        @Query("format") format: String = "json"
    ): Call<YandexGeocoderAPIAnswer>
}