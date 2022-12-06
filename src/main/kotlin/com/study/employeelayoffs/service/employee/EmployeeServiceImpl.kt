package com.study.employeelayoffs.service

import com.study.employeelayoffs.controller.toEmployee
import com.study.employeelayoffs.controller.toEmployeeResponse
import com.study.employeelayoffs.dao.EmployeeRepository
import com.study.employeelayoffs.domain.Employee
import com.study.employeelayoffs.dto.AddEmployeeRequest
import com.study.employeelayoffs.dto.EmployeeResponse
import com.study.employeelayoffs.dto.UpdateEmployeeRequest
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