package ru.gkp16.interactive_map.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import ru.gkp16.interactive_map.services.excel_service.ExcelParser
import ru.gkp16.interactive_map.services.map_service.MapService
import ru.gkp16.interactive_map.types.map_service.Address
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Paths
import ru.homyakin.iuliia.Schemas
import ru.homyakin.iuliia.Translator


@Controller
class MainController {

    val mapService = MapService()

    @GetMapping("/")
    fun addressesTable(model: Model): String {
        val addresses = mapService.getAllAddresses()
        model.addAttribute("addresses", addresses)
        return "searching_form"
    }

    @GetMapping("/regions")
    fun regionsTable(model: Model): String {
        val regions = mapService.getAllRegions()
        model.addAttribute("regions", regions)
        return "regions"
    }

    @GetMapping("/upload_file_form")
    fun uploadFileForm(model: Model): String {
        return "upload_xlsx"
    }

    @GetMapping("/file_load_ended")
    fun fileLoadEnded(model: Model, @ModelAttribute result: String): String {
        model.addAttribute("result", result)
        return "loading_ended"
    }

    @RequestMapping(value = ["/upload"], method = [RequestMethod.POST])
    fun handleFileUpload(
        redirectAttributes: RedirectAttributes,
        @RequestParam("file") file: MultipartFile
    ): String {
        var result = ""
        val name = Translator(Schemas.ICAO_DOC_9303).translate(file.originalFilename)
        if (!file.isEmpty) {
            try {
                val bytes = file.bytes
                val stream =
                    BufferedOutputStream(FileOutputStream(File(name)))
                stream.write(bytes)
                stream.close()
                val excelParser = ExcelParser.create(Paths.get("").toAbsolutePath().toString() + "\\$name")
                val addresses = mutableListOf<Address>()
                excelParser.parse(onRowParsed = { addresses.add(it) })
                mapService.updateAddresses(addresses)
                mapService.updateRegions(addresses)
            } catch (e: Exception) {
                result = e.message ?: "Unknown error"
            }
        } else {
            result = "File is empty"
        }
        redirectAttributes.addFlashAttribute("result", result)
        return "redirect:/file_load_ended"
    }
}