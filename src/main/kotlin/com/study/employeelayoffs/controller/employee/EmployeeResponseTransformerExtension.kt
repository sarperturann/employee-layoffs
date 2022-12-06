package com.study.employeelayoffs.controller.employee

import com.study.employeelayoffs.domain.Employee
import com.study.employeelayoffs.dto.employee.AddEmployeeRequest
import com.study.employeelayoffs.dto.employee.EmployeeResponse

fun Employee?.toEmployeeResponse(): EmployeeResponse {
    return EmployeeResponse(
            id = this?.id ?: 1L,
            fullName = "${this?.lastName}, ${this?.name}",
            jobTitle = "${this?.position} ${this?.job}"
    )
}

fun AddEmployeeRequest?.toEmployee(): Employee {
    return Employee(
        name = this?.name ?: "",
        lastName = this?.lastName,
        job = this?.job ?: "",
        position = this?.position
    )
}