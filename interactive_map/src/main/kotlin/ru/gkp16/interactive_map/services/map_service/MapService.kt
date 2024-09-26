package ru.gkp16.interactive_map.services.map_service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gkp16.interactive_map.types.map_service.Address

@Service
class MapService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://leafletmap:81/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(IMapService::class.java)

    private val logger = LoggerFactory.getLogger(MapService::class.java)

    fun getAllAddresses(): List<Address>? {
        try {
            val response = service.getAddresses().execute()
            return response.body()
        } catch (exception: Exception) {
            logger.error(exception.message)
        }
        return null
    }
}