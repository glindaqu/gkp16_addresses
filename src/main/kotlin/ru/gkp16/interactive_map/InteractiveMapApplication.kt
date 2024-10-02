package ru.gkp16.interactive_map

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.gkp16.interactive_map.services.excel_service.ExcelParser
import ru.gkp16.interactive_map.services.map_service.MapService
import ru.gkp16.interactive_map.types.map_service.Address

@SpringBootApplication
class InteractiveMapApplication

fun main(args: Array<String>) {
	runApplication<InteractiveMapApplication>(*args)
//	val excelParser = ExcelParser.create("D:\\Мои документы\\Desktop\\адреса по участкам.xlsx")
//	val addresses = mutableListOf<Address>()
//	excelParser.parse(onRowParsed = {
//		addresses.add(it)
//	})
//	MapService().updateAddress(addresses)
}


