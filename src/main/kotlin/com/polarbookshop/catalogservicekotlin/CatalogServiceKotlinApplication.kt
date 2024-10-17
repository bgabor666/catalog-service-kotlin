package com.polarbookshop.catalogservicekotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class CatalogServiceKotlinApplication

fun main(args: Array<String>) {
    runApplication<CatalogServiceKotlinApplication>(*args)
}
