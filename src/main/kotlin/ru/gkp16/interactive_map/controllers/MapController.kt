package ru.gkp16.interactive_map.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import ru.gkp16.interactive_map.services.map_service.MapService

@Controller
class MapController {

    val mapService = MapService()

    @GetMapping("/")
    fun map(model: Model): String {
        val addresses = mapService.getAllAddresses()
        model.addAttribute("addresses", addresses)
        return "searching_form"
    }

    @GetMapping("/regions")
    fun regionsTable(model: Model): String {
        return "regions"
    }
}