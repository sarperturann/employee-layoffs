package com.study.demorestapi.controller

import com.study.demorestapi.domain.Person
import com.study.demorestapi.dto.AddPersonRequest
import com.study.demorestapi.dto.PersonResponse

fun Person?.toPersonResponse(): PersonResponse {
    return PersonResponse(
            id = this?.id ?: 1L,
            fullName = "${this?.lastName}, ${this?.name}",
            jobTitle = "${this?.position} ${this?.job}"
    )
}

fun AddPersonRequest?.toPerson(): Person {
    return Person(
        name = this?.name ?: "",
        lastName = this?.lastName,
        job = this?.job ?: "",
        position = this?.position
    )
}