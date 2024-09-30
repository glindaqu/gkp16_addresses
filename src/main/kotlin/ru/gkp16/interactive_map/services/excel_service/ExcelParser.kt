package ru.gkp16.interactive_map.services.excel_service

import org.w3c.dom.NodeList
import ru.gkp16.interactive_map.services.xml_servive.XmlService
import ru.gkp16.interactive_map.types.map_service.Address
import java.io.File
import java.nio.file.Paths
import kotlin.jvm.Throws

class ExcelParser private constructor(private val xlsxFilepath: String) : IExcelParser {

    private val xlsxFile = File(xlsxFilepath)
    private val unpackDirectory = Paths.get("").toAbsolutePath().toString() + "/unzipped/"
    private val xmlService = XmlService.create(
        filepath = unpackDirectory + "xl/worksheets/sheet1.xml",
        stringPullPath = unpackDirectory + "xl/sharedStrings.xml"
    )

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
        ProcessBuilder().command("unzip", "-u", "-d", unpackDirectory, xlsxFilepath)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .redirectOutput(ProcessBuilder.Redirect.INHERIT).start()
            .waitFor()
    }

    private fun getDocumentsInners(rows: NodeList, onRowParsed: (Address) -> Unit) {
        for (i in 1..rows.length step 2) {
            val row = rows.item(i)
            val nextRow = rows.item(i + 1)
            if (row == null || row.childNodes == null ||
                nextRow == null || nextRow.childNodes == null) {
                continue
            }
            val addressesColumns = row.childNodes
            val flatsColumns = nextRow.childNodes
            for (j in 3..addressesColumns.length) {
                if (addressesColumns.item(0).textContent == "" || addressesColumns.item(1).textContent == ""
                    || addressesColumns.item(2).textContent == "") {
                    break
                }
                val address = Address(
                    Region = addressesColumns.item(0).textContent,
                    MedicalDivision = xmlService.getString(addressesColumns.item(24).textContent.toInt()),
                    Street = xmlService.getString(addressesColumns.item(1).textContent.toInt()),
                    Prefix = xmlService.getString(addressesColumns.item(2).textContent.toInt())
                )
                val flatNumberCell = addressesColumns.item(j)
                val peopleCountCell = flatsColumns.item(j)
                if (flatNumberCell == null || flatNumberCell.childNodes == null ||
                    flatNumberCell.childNodes.length == 0 || flatNumberCell.childNodes.item(0).nodeName != "v") {
                    break
                }
                val isString = flatNumberCell.hasAttributes()
                        && flatNumberCell.attributes.getNamedItem("t")?.textContent == "s"
                if (isString) {
                    address.HouseNumber = xmlService.getString(flatNumberCell.childNodes.item(0).textContent.toInt())
                } else {
                    address.HouseNumber = flatNumberCell.childNodes.item(0).textContent
                }

                if (peopleCountCell != null && peopleCountCell.childNodes != null &&
                        peopleCountCell.childNodes.length != 0 && peopleCountCell.childNodes.item(0).nodeName == "v") {
                    address.FlatCount = peopleCountCell.childNodes.item(0).textContent
                }
                onRowParsed(address)
            }
        }
    }

    @Throws(ExcelParserException::class)
    override fun parse(onRowParsed: (Address) -> Unit) {
        prepare()
        unzip()

        val rows = xmlService.read().getElementsByTagName("row")

        if (rows == null) {
            throw ExcelParserException.INVALID_XLSX_FILE_STRUCTURE
        }

        getDocumentsInners(
            rows = rows,
            onRowParsed = onRowParsed
        )
    }
}