package com.study.demorestapi.service

import com.study.demorestapi.controller.toPerson
import com.study.demorestapi.controller.toPersonResponse
import com.study.demorestapi.dao.PersonRepository
import com.study.demorestapi.domain.Person
import com.study.demorestapi.dto.AddPersonRequest
import com.study.demorestapi.dto.PersonResponse
import com.study.demorestapi.dto.UpdatePersonRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl (
    private val personDao: PersonRepository,
) : PersonService {
    override fun findById(id: Long): PersonResponse? = this.findPersonById(id).toPersonResponse()

    override fun findAll(): List<PersonResponse> = this.personDao.findAll().map(Person::toPersonResponse)

    override fun save(addPersonRequest: AddPersonRequest): PersonResponse {
        return this.saveOrUpdate(addPersonRequest.toPerson())
    }

    override fun update(updatePersonRequest: UpdatePersonRequest): PersonResponse {
        val selectedPerson = findPersonById(updatePersonRequest.id)
                ?: throw IllegalStateException("${updatePersonRequest.id} not found")
        return this.saveOrUpdate(selectedPerson.apply {
            this.name = updatePersonRequest.name
            this.lastName = updatePersonRequest.lastName
            this.job = updatePersonRequest.job
            this.position = updatePersonRequest.position
        })
    }

    override fun deleteById(id: Long) = this.personDao.deleteById(id)

    private fun findPersonById (id: Long): Person? = this.personDao.findByIdOrNull(id)

    private fun saveOrUpdate (person: Person): PersonResponse = this.personDao.save(person).toPersonResponse()
}