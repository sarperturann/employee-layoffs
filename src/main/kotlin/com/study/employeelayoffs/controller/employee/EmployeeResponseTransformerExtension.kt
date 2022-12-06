package com.study.employeelayoffs.controller

import com.study.employeelayoffs.domain.Employee
import com.study.employeelayoffs.dto.AddEmployeeRequest
import com.study.employeelayoffs.dto.EmployeeResponse

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