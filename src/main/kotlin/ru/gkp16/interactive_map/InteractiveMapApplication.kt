package ru.gkp16.interactive_map

import jakarta.servlet.MultipartConfigElement
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.MultipartConfigFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.util.unit.DataSize
import ru.gkp16.interactive_map.services.excel_service.ExcelParser
import ru.gkp16.interactive_map.services.map_service.MapService
import ru.gkp16.interactive_map.types.map_service.Address


@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
class InteractiveMapApplication {
	@Bean
	fun multipartConfigElement(): MultipartConfigElement {
		val factory = MultipartConfigFactory()
		factory.setMaxFileSize(DataSize.ofMegabytes(10))
		factory.setMaxRequestSize(DataSize.ofMegabytes(10))
		return factory.createMultipartConfig()
	}
}

fun main(args: Array<String>) {
	runApplication<InteractiveMapApplication>(*args)
}


