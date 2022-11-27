package com.study.demorestapi

import com.study.demorestapi.domain.Employee
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class DemoRestApiApplication {


}

fun main(args: Array<String>) {
	runApplication<DemoRestApiApplication>(*args)
}
