package com.study.employeelayoffs.dto.employee

data class UpdateEmployeeRequest (
        val id: Long,
        val name: String,
        val lastName: String?,
        val job: String,
        val position: String?
)