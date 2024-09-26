package ru.gkp16.interactive_map.services.xml_servive

import org.w3c.dom.Document
import org.xml.sax.InputSource
import java.io.File
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

class XmlService private constructor(filepath: String) {

    private val file = File(filepath)
    private lateinit var document: Document

    companion object {
        fun create(filepath: String): XmlService = XmlService(filepath)
    }

    fun read(): Document {
        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()
        val xmlInput = InputSource(StringReader(file.readText()))
        document = dBuilder.parse(xmlInput)
        return document
    }
}