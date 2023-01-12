package com.study.employeelayoffs.employee

import com.study.employeelayoffs.employee.dto.AddEmployeeRequest
import com.study.employeelayoffs.employee.dto.EmployeeResponse
import com.study.employeelayoffs.employee.dto.UpdateEmployeeRequest

interface EmployeeService {
    fun findById (id: Long): EmployeeResponse?
    fun findAll (): List<EmployeeResponse>
    fun save (addEmployeeRequest: AddEmployeeRequest): EmployeeResponse
    fun update (id: Long, updateEmployeeRequest: UpdateEmployeeRequest): EmployeeResponse
    fun deleteById (id: Long)
}