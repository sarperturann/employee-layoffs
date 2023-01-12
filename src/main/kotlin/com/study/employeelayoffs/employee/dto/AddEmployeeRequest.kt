package com.study.employeelayoffs.employee.dto

data class AddEmployeeRequest (
        val name: String,
        val lastName: String?,
        val job: String,
        val position: String?
)