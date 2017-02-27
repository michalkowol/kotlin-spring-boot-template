package pl.michalkowol.people

import com.ninja_squad.dbsetup.DbSetup
import com.ninja_squad.dbsetup.Operations
import com.ninja_squad.dbsetup.destination.DataSourceDestination
import com.ninja_squad.dbsetup.operation.Operation
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.test.context.junit4.SpringRunner
import pl.michalkowol.cities.CitiesRepository
import pl.michalkowol.cities.City
import java.net.URI
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

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

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

    @Test
    fun `it should do REST request`() {
        // when
        val json = restTemplate.getForObject("/people", String::class.java)
        val xml = restTemplate.exchange(RequestEntity.get(URI("/people")).accept(MediaType.APPLICATION_XML).build(), String::class.java)
        // then
        assertTrue("entity should be json", json.startsWith("""[{"id":"""))
        assertTrue("entity should be xml", xml.body.startsWith("""<ArrayList><item><id>"""))
    }

    private fun prepareDatabase(operation: Operation) {
        DbSetup(DataSourceDestination(dataSource), operation).launch()
    }
}
