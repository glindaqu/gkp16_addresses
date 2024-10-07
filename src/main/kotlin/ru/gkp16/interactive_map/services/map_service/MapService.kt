package ru.gkp16.interactive_map.services.map_service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gkp16.interactive_map.services.yandex_geocoder_service.YandexGeocoderService
import ru.gkp16.interactive_map.types.map_service.Address
import ru.gkp16.interactive_map.types.map_service.Region

@Service
class MapService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://leafletmap:81/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(IMapService::class.java)
    private val geocoderService = YandexGeocoderService()

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

    fun getAllRegions(): List<Region>? {
        try {
            val response = service.getRegions().execute()
            return response.body()
        } catch (exception: Exception) {
            logger.error(exception.message)
        }
        return null
    }

    fun isAddressExists(address: Address): Boolean {
        if (address.Street == null || address.HouseNumber == null) {
            logger.error("invalid address given: $address")
            return false
        }
        try {
            val response = service.isAddressExists(address.Street!!, address.HouseNumber!!)
            return response.execute().body()!!
        } catch (e: Exception) {
            logger.error(e.message)
        }
        return false
    }

    fun updateAddress(addresses: List<Address>) {
        addresses.forEach {
            if (it.Street != null && it.HouseNumber != null) {
                if (isAddressExists(it)) {
                    service.updateAddress(
                        street = it.Street!!,
                        houseNumber = it.HouseNumber!!,
                        flatCount = it.FlatCount,
                        medicalDivision = it.MedicalDivision,
                        region = it.Region
                    ).execute()
                } else {
                    geocoderService.getAddressCoordinates(it, onFailure = {}, onSuccess = { point ->
                        if (point != null) {
                            service.insertAddress(
                                prefix = it.Prefix!!,
                                street = it.Street!!,
                                flatCount = it.FlatCount!!,
                                region = it.Region!!,
                                houseNumber = it.HouseNumber!!,
                                medicalDivision = it.MedicalDivision!!,
                                latitude = point.pos?.split(" ")?.get(0) ?: "0",
                                longitude = point.pos?.split(" ")?.get(1) ?: "0"
                            ).execute()
                        }
                    })
                }
            }
        }
    }
}