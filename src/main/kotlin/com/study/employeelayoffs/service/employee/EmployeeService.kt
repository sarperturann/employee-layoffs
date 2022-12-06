package com.study.employeelayoffs.service.employee

import com.study.employeelayoffs.dto.employee.AddEmployeeRequest
import com.study.employeelayoffs.dto.employee.EmployeeResponse
import com.study.employeelayoffs.dto.employee.UpdateEmployeeRequest

interface EmployeeService {
    fun findById (id: Long): EmployeeResponse?
    fun findAll (): List<EmployeeResponse>
    fun save (addEmployeeRequest: AddEmployeeRequest): EmployeeResponse
    fun update (updateEmployeeRequest: UpdateEmployeeRequest): EmployeeResponse
    fun deleteById (id: Long)
}