package com.study.employeelayoffs.service

import com.study.employeelayoffs.dto.AddEmployeeRequest
import com.study.employeelayoffs.dto.EmployeeResponse
import com.study.employeelayoffs.dto.UpdateEmployeeRequest

interface EmployeeService {
    fun findById (id: Long): EmployeeResponse?
    fun findAll (): List<EmployeeResponse>
    fun save (addEmployeeRequest: AddEmployeeRequest): EmployeeResponse
    fun update (updateEmployeeRequest: UpdateEmployeeRequest): EmployeeResponse
    fun deleteById (id: Long)
}