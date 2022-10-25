package com.study.demorestapi.resources

import com.study.demorestapi.dto.AddPersonRequest
import com.study.demorestapi.dto.PersonResponse
import com.study.demorestapi.dto.UpdatePersonRequest
import com.study.demorestapi.resources.PersonResourceImpl.Companion.BASE_VERSION_URL
import com.study.demorestapi.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping(value = [BASE_VERSION_URL])
class PersonResourceImpl (
        private val personService: PersonService
) : PersonResource {
    @GetMapping("/{id}")
    override fun findById(@PathVariable id: Long): ResponseEntity<PersonResponse?> {
        val personResponse: PersonResponse? = this.personService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(personResponse)
    }

    @GetMapping()
    override fun findAll(): ResponseEntity<List<PersonResponse>> = ResponseEntity.ok(this.personService.findAll())

    @PostMapping
    override fun save(@RequestBody addPersonRequest: AddPersonRequest): ResponseEntity<PersonResponse> {
        val personResponse: PersonResponse? = this.personService.save(addPersonRequest)
        return ResponseEntity.created(URI.create(BASE_VERSION_URL.plus("/${personResponse?.id}")))
                .body(personResponse)
    }

    @PutMapping
    override fun update(@RequestBody updatePersonRequest: UpdatePersonRequest): ResponseEntity<PersonResponse> {
        return ResponseEntity.ok(this.personService.update(updatePersonRequest))
    }

    @DeleteMapping("/{id}")
    override fun deleteById(@PathVariable id: Long): ResponseEntity<Unit> {
        this.personService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    companion object {
        const val BASE_VERSION_URL: String = "api/v1/person"
    }
}