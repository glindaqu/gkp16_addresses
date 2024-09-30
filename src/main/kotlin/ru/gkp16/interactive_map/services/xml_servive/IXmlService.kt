package ru.gkp16.interactive_map.services.xml_servive

import org.w3c.dom.Document

interface IXmlService {
    fun read(): Document
    fun getString(index: Int): String?
}