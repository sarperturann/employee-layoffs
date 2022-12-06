package com.study.employeelayoffs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@RestController
class EmployeeLayoffApplication {}

fun main(args: Array<String>) {
	runApplication<EmployeeLayoffApplication>(*args)
}
