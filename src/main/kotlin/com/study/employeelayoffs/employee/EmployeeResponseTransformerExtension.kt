package com.study.employeelayoffs.employee

import com.study.employeelayoffs.employee.dto.AddEmployeeRequest
import com.study.employeelayoffs.employee.dto.EmployeeResponse

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