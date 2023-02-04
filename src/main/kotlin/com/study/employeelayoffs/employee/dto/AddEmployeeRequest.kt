package com.study.employeelayoffs.employee.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class AddEmployeeRequest (
        val name: String,
        val lastName: String?,
        val job: String,
        val position: String?,
        val team: String? = null,
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
        val startingDate: Date? = null,
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
        val lastUpdateDate: Date? = null,
        val linesOfCodeWritten: Int? = 0,
)