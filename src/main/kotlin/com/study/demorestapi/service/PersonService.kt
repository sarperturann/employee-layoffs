package com.study.demorestapi.service

import com.study.demorestapi.dto.AddPersonRequest
import com.study.demorestapi.dto.PersonResponse
import com.study.demorestapi.dto.UpdatePersonRequest

interface PersonService {
    fun findById (id: Long): PersonResponse?
    fun findAll (): List<PersonResponse>
    fun save (addPersonRequest: AddPersonRequest): PersonResponse
    fun update (updatePersonRequest: UpdatePersonRequest): PersonResponse
    fun deleteById (id: Long)
}