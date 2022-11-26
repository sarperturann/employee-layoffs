package com.study.demorestapi.service

import com.study.demorestapi.dto.AddEmployeeRequest
import com.study.demorestapi.dto.EmployeeResponse
import com.study.demorestapi.dto.UpdateEmployeeRequest

interface EmployeeService {
    fun findById (id: Long): EmployeeResponse?
    fun findAll (): List<EmployeeResponse>
    fun save (addEmployeeRequest: AddEmployeeRequest): EmployeeResponse
    fun update (updateEmployeeRequest: UpdateEmployeeRequest): EmployeeResponse
    fun deleteById (id: Long)
}