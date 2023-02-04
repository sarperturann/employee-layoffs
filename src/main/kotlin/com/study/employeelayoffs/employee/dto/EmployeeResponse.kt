package com.study.employeelayoffs.employee.dto

data class EmployeeResponse (
    val id: Long,
    val fullName: String,
    val jobTitle: String,
    val job: String,
    val team: String,
    val startingDate: String,
    val timeSpent: String,
    val timeSpentTillLastUpdate: String,
    val efficiency: String
)