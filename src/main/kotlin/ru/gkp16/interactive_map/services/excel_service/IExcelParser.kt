package ru.gkp16.interactive_map.services.excel_service

import ru.gkp16.interactive_map.types.map_service.Address

interface IExcelParser {
    fun parse(onRowParsed: (Address) -> Unit)
}