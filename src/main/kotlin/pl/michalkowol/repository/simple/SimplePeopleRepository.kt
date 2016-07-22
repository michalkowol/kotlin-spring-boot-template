package pl.michalkowol.repository.simple

import org.springframework.stereotype.Repository
import pl.michalkowol.model.simple.Address
import pl.michalkowol.model.simple.Person

/*
| Annotation | Meaning                                             |
+------------+-----------------------------------------------------+
| @Component | generic stereotype for any Spring-managed component |
| @Repository| stereotype for persistence layer                    |
| @Service   | stereotype for service layer                        |
| @Controller| stereotype for presentation layer (spring-mvc)      |
 */

@Repository
open class SimplePeopleRepository {
    /*
    Why does it have `open` modifier?
    Unable to proxy method [public final java.util.List pl.michalkowol.repository.simple.SimplePeopleRepository.findByName(java.lang.String)]
    because it is final: All calls to this method via a proxy will NOT be routed to the target instance.
     */
    open fun findOne(id: Long): Person? {
        if (id == 1L) {
            return Person("Michal", listOf(Address("Chemiczna", "Gliwice"), Address("Pulawska", "Warszawa")))
        } else if (id == 2L) {
            return Person("Kasia", listOf(Address("Przybylskiego", "Warszawa")))
        }
        return null
    }

    open fun findAll(): List<Person> {
        val people = listOf(findOne(1), findOne(2)).filterNotNull()
        return people
    }

    open fun findByName(name: String): List<Person> {
        val people = listOf(findOne(1), findOne(2)).filterNotNull()
        return people.filter { it.name == name }
    }
}