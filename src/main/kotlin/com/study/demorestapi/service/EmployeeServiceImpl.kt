package com.study.demorestapi.service

import com.study.demorestapi.controller.toEmployee
import com.study.demorestapi.controller.toEmployeeResponse
import com.study.demorestapi.dao.EmployeeRepository
import com.study.demorestapi.domain.Employee
import com.study.demorestapi.dto.AddEmployeeRequest
import com.study.demorestapi.dto.EmployeeResponse
import com.study.demorestapi.dto.UpdateEmployeeRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl (
        private val employeeDao: EmployeeRepository,
) : EmployeeService {
    override fun findById(id: Long): EmployeeResponse? = this.findEmployeeById(id).toEmployeeResponse()

    override fun findAll(): List<EmployeeResponse> = this.employeeDao.findAll().map(Employee::toEmployeeResponse)

    override fun save(addEmployeeRequest: AddEmployeeRequest): EmployeeResponse {
        return this.saveOrUpdate(addEmployeeRequest.toEmployee())
    }

    override fun update(updateEmployeeRequest: UpdateEmployeeRequest): EmployeeResponse {
        val selectedPerson = findEmployeeById(updateEmployeeRequest.id)
                ?: throw IllegalStateException("${updateEmployeeRequest.id} not found")
        return this.saveOrUpdate(selectedPerson.apply {
            this.name = updateEmployeeRequest.name
            this.lastName = updateEmployeeRequest.lastName
            this.job = updateEmployeeRequest.job
            this.position = updateEmployeeRequest.position
        })
    }

    override fun deleteById(id: Long) = this.employeeDao.deleteById(id)

    private fun findEmployeeById (id: Long): Employee? = this.employeeDao.findByIdOrNull(id)

    private fun saveOrUpdate (employee: Employee): EmployeeResponse = this.employeeDao.save(employee).toEmployeeResponse()
}