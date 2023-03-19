package com.study.employeelayoffs.employee

import com.study.employeelayoffs.common.exception.NotFoundException
import com.study.employeelayoffs.employee.dto.AddEmployeeRequest
import com.study.employeelayoffs.employee.dto.EmployeeResponse
import com.study.employeelayoffs.employee.dto.UpdateEmployeeRequest
import com.study.employeelayoffs.rabbitmq.Producer
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl (
    private val employeeRepository: EmployeeRepository,
    private val rabbitMqProducer: Producer
) : EmployeeService {
    override fun findById(id: Long): EmployeeResponse? {
        val employee = this.employeeRepository.findById(id)
        if (employee.isEmpty)
            throw NotFoundException(String.format("Employee with ID %d not found", id))
        return employee.get().toEmployeeResponse()
    }

    override fun findAll(): List<EmployeeResponse> = this.employeeRepository.findAll().map(Employee::toEmployeeResponse)

    override fun save(addEmployeeRequest: AddEmployeeRequest): EmployeeResponse {
        rabbitMqProducer.sendMessage("Employee ${addEmployeeRequest.name} ${addEmployeeRequest.lastName} added")
        return this.saveOrUpdate(addEmployeeRequest.toEmployee())
    }

    override fun update(id: Long, updateEmployeeRequest: UpdateEmployeeRequest): EmployeeResponse {
        val selectedEmployee = this.employeeRepository.findById(id)
        if (selectedEmployee.isEmpty)
            throw NotFoundException(String.format("Employee with ID %d not found", id))
        rabbitMqProducer.sendMessage("Employee with id $id updated")
        return this.saveOrUpdate(selectedEmployee.get().apply {
            this.name = updateEmployeeRequest.name
            this.lastName = updateEmployeeRequest.lastName
            this.job = updateEmployeeRequest.job
            this.position = updateEmployeeRequest.position
            this.team = updateEmployeeRequest.team
            this.startingDate = updateEmployeeRequest.startingDate
            this.lastUpdateDate = updateEmployeeRequest.lastUpdateDate
            this.linesOfCodeWritten = updateEmployeeRequest.linesOfCodeWritten
        })
    }

    override fun deleteById(id: Long) {
        val employee = this.employeeRepository.findById(id)
        if (employee.isEmpty)
            throw NotFoundException(String.format("Employee with ID %d not found", id))
        rabbitMqProducer.sendMessage("Employee with id $id deleted")
        return this.employeeRepository.deleteById(id)
    }

    private fun saveOrUpdate (employee: Employee): EmployeeResponse = this.employeeRepository.save(employee).toEmployeeResponse()
}