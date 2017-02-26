package pl.michalkowol.people

import org.springframework.data.repository.CrudRepository
import java.util.*

internal interface PeopleRepository : CrudRepository<Person, UUID> {
    fun findByName(name: String): List<Person>
}
