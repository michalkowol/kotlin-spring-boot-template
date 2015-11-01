package pl.michalkowol.repository

import org.springframework.data.repository.CrudRepository
import pl.michalkowol.model.jpa.Person

interface PeopleRepository : CrudRepository<Person, Long> {
    fun findByName(name: String): List<Person>
}
