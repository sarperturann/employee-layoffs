package com.study.demorestapi

import com.study.demorestapi.domain.Person
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class DemoRestApiApplication {

	@GetMapping
	fun hi(): Person {
		return Person(name = "sarper")
	}
}

fun main(args: Array<String>) {
	runApplication<DemoRestApiApplication>(*args)
}
