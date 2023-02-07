package com.study.employeelayoffs.employee

import com.study.employeelayoffs.employee.dto.AddEmployeeRequest
import com.study.employeelayoffs.employee.dto.EmployeeResponse
import com.study.employeelayoffs.employee.dto.UpdateEmployeeRequest
import com.study.employeelayoffs.employee.EmployeeController.Companion.BASE_VERSION_URL
import com.study.employeelayoffs.rabbitmq.Producer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = [BASE_VERSION_URL])
class EmployeeController (
        private val employeeService: EmployeeService,
        private val rabbitMqProducer: Producer
) {
    val LOGGER: Logger = LoggerFactory.getLogger(EmployeeController::class.java)

    @GetMapping("/")
    fun findById(@RequestParam id: Long): EmployeeResponse? {
        return employeeService.findById(id)
    }

    @GetMapping()
    fun findAll(): List<EmployeeResponse> = employeeService.findAll()

    @PostMapping
    fun save(@RequestBody addEmployeeRequest: AddEmployeeRequest): EmployeeResponse = employeeService.save(addEmployeeRequest)

    @PutMapping("/")
    fun update(@RequestParam id: Long, @RequestBody updateEmployeeRequest: UpdateEmployeeRequest): EmployeeResponse = employeeService.update(id, updateEmployeeRequest)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) {
        rabbitMqProducer.sendMessage("test message")
        employeeService.deleteById(id)
    }

    companion object {
        const val BASE_VERSION_URL: String = "api/v1/person"
    }
}