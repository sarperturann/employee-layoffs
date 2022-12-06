package com.study.employeelayoffs.controller.employee

import com.study.employeelayoffs.dto.employee.AddEmployeeRequest
import com.study.employeelayoffs.dto.employee.EmployeeResponse
import com.study.employeelayoffs.dto.employee.UpdateEmployeeRequest
import com.study.employeelayoffs.controller.employee.EmployeeController.Companion.BASE_VERSION_URL
import com.study.employeelayoffs.service.employee.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping(value = [BASE_VERSION_URL])
class EmployeeController (
        private val employeeService: EmployeeService
) {
    @GetMapping("/")
    fun findById(@RequestParam id: Long): ResponseEntity<EmployeeResponse?> {
        val employeeResponse: EmployeeResponse? = this.employeeService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse)
    }

    @GetMapping()
    fun findAll(): ResponseEntity<List<EmployeeResponse>> = ResponseEntity.ok(this.employeeService.findAll())

    @PostMapping
    fun save(@RequestBody addEmployeeRequest: AddEmployeeRequest): ResponseEntity<EmployeeResponse> {
        val employeeResponse: EmployeeResponse = this.employeeService.save(addEmployeeRequest)
        return ResponseEntity.created(URI.create(BASE_VERSION_URL.plus("/${employeeResponse.id}")))
                .body(employeeResponse)
    }

    @PutMapping
    fun update(@RequestBody updateEmployeeRequest: UpdateEmployeeRequest): ResponseEntity<EmployeeResponse> {
        return ResponseEntity.ok(this.employeeService.update(updateEmployeeRequest))
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Unit> {
        this.employeeService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    companion object {
        const val BASE_VERSION_URL: String = "api/v1/person"
    }
}