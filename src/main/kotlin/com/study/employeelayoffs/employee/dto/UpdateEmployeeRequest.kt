package com.study.employeelayoffs.employee.dto

import java.util.*

data class UpdateEmployeeRequest (
        val id: Long,
        val name: String,
        val lastName: String?,
        val job: String,
        val position: String?,
        val team: String? = null,
        val startingDate: Date? = null,
        val lastUpdateDate: Date? = null,
        val linesOfCodeWritten: Int? = 0
)