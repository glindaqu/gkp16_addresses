package ru.gkp16.interactive_map.services.xml_servive

class XmlServiceException private constructor(override val message: String): Exception() {
    companion object {
        val INVALID_FILE_STRUCTURE = XmlServiceException("Given file doesn't contain strings")
        val FILE_DOES_NOT_EXISTS = XmlServiceException("File variable isn't been initialized. Seems like something went wrong")
    }
}