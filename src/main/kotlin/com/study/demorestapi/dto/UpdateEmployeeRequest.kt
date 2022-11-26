package com.study.demorestapi.dto

data class UpdateEmployeeRequest (
        val id: Long,
        val name: String,
        val lastName: String?,
        val job: String,
        val position: String?
)