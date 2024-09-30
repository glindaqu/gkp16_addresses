package ru.gkp16.interactive_map.services.excel_service

class ExcelParserException private constructor(override val message: String) : Exception() {
    companion object {
        val FILE_DOES_NOT_EXISTS = ExcelParserException("Seems like given file isn't exists")
        val FILEPATH_NOT_SPECIFIED = ExcelParserException("Seems like you are didn't specify filepath in create method")
        val NOT_XLSX_FILE_EXTENSION = ExcelParserException("Seems like given file isn't .xlsx file")
        val INVALID_XLSX_FILE_STRUCTURE =
            ExcelParserException("Seems like given file has unsupported structure. It can't been parsed")
    }
}