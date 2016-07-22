package pl.michalkowol.repository.simple

import pl.michalkowol.model.simple.Address
import pl.michalkowol.model.simple.Person
import spock.lang.*

@Unroll
class SimplePeopleRepositorySpec extends Specification {

    def "`SimplePeopleRepository` should return `Person` by `id`"() {
        given:
        def simplePeopleRepository = new SimplePeopleRepository()

        expect:
        simplePeopleRepository.findOne(id) == person

        where:
        id || person
        1  || new Person("Michal", [new Address("Chemiczna", "Gliwice"), new Address("Pulawska", "Warszawa")])
        2  || new Person("Kasia", [new Address("Przybylskiego", "Warszawa")])
        3  || null
    }
}
