package com.study.demorestapi.dto

data class UpdatePersonRequest (
        val id: Long,
        val name: String,
        val lastName: String?,
        val job: String,
        val position: String?
)