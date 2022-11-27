package com.study.employeelayoffs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class EmployeeLayoffApplication {}

fun main(args: Array<String>) {
	runApplication<EmployeeLayoffApplication>(*args)
}
