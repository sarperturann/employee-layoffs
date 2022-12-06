package com.study.employeelayoffs.dto.employee

data class AddEmployeeRequest (
        val name: String,
        val lastName: String?,
        val job: String,
        val position: String?
)