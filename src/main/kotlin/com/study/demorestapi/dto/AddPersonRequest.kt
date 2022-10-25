package com.study.demorestapi.dto

data class AddPersonRequest (
        val name: String,
        val lastName: String?,
        val job: String,
        val position: String?
)