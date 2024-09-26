package ru.gkp16.interactive_map.services.excel_service

import ru.gkp16.interactive_map.services.xml_servive.XmlService
import java.io.File
import java.nio.file.Paths
import kotlin.jvm.Throws

class ExcelParser private constructor(private val xlsxFilepath: String) : IExcelParser {

    private val xlsxFile = File(xlsxFilepath)
    private val unpackDirectory = Paths.get("").toAbsolutePath().toString() + "/unzipped/"
    private val xmlService = XmlService.create(unpackDirectory + "xl/worksheets/sheet1.xml")

    companion object {
        fun create(filepath: String): ExcelParser = ExcelParser(filepath)
    }

    @Throws(ExcelParserException::class)
    private fun prepare() {
        if (xlsxFilepath.isEmpty()) {
            throw ExcelParserException.FILEPATH_NOT_SPECIFIED
        } else if (!xlsxFile.exists()) {
            throw ExcelParserException.FILE_DOES_NOT_EXISTS
        } else if (xlsxFile.extension != "xlsx") {
            throw ExcelParserException.NOT_XLSX_FILE_EXTENSION
        }
    }

    private fun unzip() {
        ProcessBuilder()
            .command("unzip", "-u", "-d", unpackDirectory, xlsxFilepath)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .redirectOutput(ProcessBuilder.Redirect.INHERIT)
            .start()
            .waitFor()
    }

    override fun parse() {
        prepare()
        unzip()
        val document = xmlService.read()
    }
}