package com.study.demorestapi.controller

import com.study.demorestapi.domain.Employee
import com.study.demorestapi.dto.AddEmployeeRequest
import com.study.demorestapi.dto.EmployeeResponse

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