package pl.michalkowol

import com.ninja_squad.dbsetup.DbSetup
import com.ninja_squad.dbsetup.Operations
import com.ninja_squad.dbsetup.Operations.deleteAllFrom
import com.ninja_squad.dbsetup.Operations.insertInto
import com.ninja_squad.dbsetup.destination.DataSourceDestination
import com.ninja_squad.dbsetup.operation.Operation
import com.softwareberg.Database
import com.softwareberg.Extractor
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class DatabaseSpec {

    private data class Person(val id: UUID, val name: String, val age: Int)

    private val dataSource = createLocalDataSource()
    private val db = Database(dataSource)
    private val personExtractor: Extractor<Person> = { rs -> Person(rs.getObject("id") as UUID, rs.getString("name"), rs.getInt("age")) }

    private val deleteAll = deleteAllFrom(listOf("addresses_people", "people"))
    private val insertTwoPeople = insertInto("people")
        .columns("id", "name", "age")
        .values(UUID.fromString("deadbeef-dead-beef-dead-beef00000001"), "Michal", 28)
        .values(UUID.fromString("deadbeef-dead-beef-dead-beef00000002"), "Kasia", 27)
        .build()

    @Test
    fun `it should setup db`() {
        // given
        prepareDatabase(Operations.sequenceOf(deleteAll, insertTwoPeople))
        // when
        val people = db.findAll("SELECT id, name, age FROM people", personExtractor)
        // then
        assertEquals(28, people[0].age)
        assertEquals(27, people[1].age)
    }

    private fun prepareDatabase(operation: Operation) {
        DbSetup(DataSourceDestination(dataSource), operation).launch()
    }

    private fun createLocalDataSource(): HikariDataSource {
        val config = HikariConfig("/datasource.properties")
        return HikariDataSource(config)
    }
}
