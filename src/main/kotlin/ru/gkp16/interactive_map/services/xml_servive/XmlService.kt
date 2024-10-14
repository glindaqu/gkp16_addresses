package ru.gkp16.interactive_map.services.xml_servive

import org.w3c.dom.Document
import org.xml.sax.InputSource
import java.io.File
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.jvm.Throws

class XmlService private constructor(filepath: String, private val stringPullPath: String) : IXmlService {

    private val file = File(filepath)
    private val stringPull = mutableListOf<String>()

    companion object {
        fun create(filepath: String, stringPullPath: String): XmlService {
            val service = XmlService(filepath, stringPullPath)
            service.initStringPull()
            return service
        }
    }

    override fun getString(index: Int): String? {
        if (index < stringPull.size) {
            return stringPull[index]
        }
        return null
    }

    @Throws(XmlServiceException::class)
    private fun initStringPull() {
        val sharedStringsFile = File(stringPullPath)

        if (!sharedStringsFile.exists()) {
            throw XmlServiceException.FILE_DOES_NOT_EXISTS
        }

        val stringPullDocument = DocumentBuilderFactory
            .newInstance()
            .newDocumentBuilder()
            .parse(InputSource(StringReader(sharedStringsFile.readText())))

        val strings = stringPullDocument.getElementsByTagName("t")

        if (strings == null) {
            throw XmlServiceException.INVALID_FILE_STRUCTURE
        }

        for (i in 0..strings.length) {
            val string = strings.item(i)
            if (string != null) {
                stringPull.add(strings.item(i).textContent)
            }
        }
    }

    @Throws(XmlServiceException::class)
    override fun read(): Document {
        if (!file.exists()) {
            throw XmlServiceException.FILE_DOES_NOT_EXISTS
        }
        return DocumentBuilderFactory
            .newInstance()
            .newDocumentBuilder()
            .parse(InputSource(StringReader(file.readText())))
    }
}