package com.michalkowol.people

import org.springframework.data.repository.CrudRepository
import java.util.*

interface PeopleRepository : CrudRepository<Person, UUID> {
    fun findByName(name: String): List<Person>
}
