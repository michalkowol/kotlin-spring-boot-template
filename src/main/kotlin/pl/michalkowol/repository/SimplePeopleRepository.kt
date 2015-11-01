package pl.michalkowol.repository

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
    fun byId(id: String): Person? {
        if (id == "1") {
            return Person("Michal", listOf(Address("Chemiczna", "Gliwice"), Address("Pulawska", "Warszawa")))
        } else if (id == "2") {
            return Person("Kasia", listOf(Address("Przybylskiego", "Warszawa")))
        }
        return null
    }

    fun list(): List<Person> {
        val people = listOf(byId("1"), byId("2")).filterNotNull()
        return people
    }
}