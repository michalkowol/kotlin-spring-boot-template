package pl.michalkowol.repository.jpa

import org.springframework.data.repository.CrudRepository
import pl.michalkowol.model.jpa.Person
import java.util.*

interface PeopleRepository : CrudRepository<Person, UUID> {
    fun findByName(name: String): List<Person>
}
