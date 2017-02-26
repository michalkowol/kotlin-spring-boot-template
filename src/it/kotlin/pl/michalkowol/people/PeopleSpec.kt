package pl.michalkowol.people

import com.ninja_squad.dbsetup.DbSetup
import com.ninja_squad.dbsetup.Operations
import com.ninja_squad.dbsetup.destination.DataSourceDestination
import com.ninja_squad.dbsetup.operation.Operation
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import pl.michalkowol.cities.CitiesRepository
import pl.michalkowol.cities.City
import javax.sql.DataSource

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PeopleSpec {

    @Autowired
    private lateinit var peopleRepository: PeopleRepository

    @Autowired
    private lateinit var addressesRepository: AddressesRepository

    @Autowired
    private lateinit var citiesRepository: CitiesRepository

    @Autowired
    private lateinit var dataSource: DataSource

    private val deleteAll = Operations.deleteAllFrom(listOf("addresses_people", "people", "addresses", "cities"))

    @Test
    fun `it should find all people`() {
        // given
        prepareDatabase(Operations.sequenceOf(deleteAll))
        val city = City.create("Gliwice")
        val addressA = Address.create("Pulawska", city)
        val addressB = Address.create("Zlota", city)
        val personA = Person.create("Michal", 29, listOf(addressA, addressB))
        val personB = Person.create("Kasia", 21, listOf(addressB))
        citiesRepository.save(city)
        addressesRepository.save(addressA)
        addressesRepository.save(addressB)
        peopleRepository.save(personA)
        peopleRepository.save(personB)
        // when
        val people = peopleRepository.findAll().toList()
        // then
        assertEquals(29, people[0].age)
        assertEquals(21, people[1].age)
    }

    private fun prepareDatabase(operation: Operation) {
        DbSetup(DataSourceDestination(dataSource), operation).launch()
    }
}
