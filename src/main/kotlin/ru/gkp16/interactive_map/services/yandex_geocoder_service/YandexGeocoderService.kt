package ru.gkp16.interactive_map.services.yandex_geocoder_service

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gkp16.interactive_map.types.map_service.Address
import ru.gkp16.interactive_map.types.yandex_geocoder_service.Point
import ru.gkp16.interactive_map.types.yandex_geocoder_service.YandexGeocoderAPIAnswer

class YandexGeocoderService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://geocode-maps.yandex.ru/1.x/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(IYandexGeocoderService::class.java)

    fun getAddressCoordinates(address: Address, onSuccess: (Point?) -> Unit, onFailure: () -> Unit) {
        service
            .getData(query = "Новосибирск+${address.Street}+${address.HouseNumber}")
            .enqueue(object : Callback<YandexGeocoderAPIAnswer> {
                override fun onResponse(
                    call: Call<YandexGeocoderAPIAnswer?>,
                    response: Response<YandexGeocoderAPIAnswer?>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body()?.response?.GeoObjectCollection?.featureMember?.get(0)?.GeoObject?.Point)
                    }
                }

                override fun onFailure(call: Call<YandexGeocoderAPIAnswer?>, t: Throwable) {
                    onFailure()
                }
            })
    }
}