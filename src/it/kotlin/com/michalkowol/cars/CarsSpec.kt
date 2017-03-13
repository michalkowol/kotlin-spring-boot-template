package com.michalkowol.cars

import com.ninja_squad.dbsetup.DbSetup
import com.ninja_squad.dbsetup.Operations.*
import com.ninja_squad.dbsetup.destination.DataSourceDestination
import com.ninja_squad.dbsetup.operation.Operation
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.IsEqual.equalTo
import org.hamcrest.core.StringStartsWith.startsWith
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner
import javax.inject.Inject
import javax.sql.DataSource

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarsSpec {

    @Inject
    private lateinit var carsController: CarsController

    @Inject
    private lateinit var carsRepository: CarsRepository

    @Inject
    private lateinit var restTemplate: TestRestTemplate

    @Inject
    private lateinit var dataSource: DataSource

    private val deleteAllCars = deleteAllFrom("cars")

    private val insertCars = insertInto("cars")
            .columns("id", "name")
            .values(1, "Audi")
            .values(2, "Opel")
            .values(3, "BMW")
            .build()

    @Test
    fun itShouldSelectAllCars() {
        // given
        prepareDatabase(sequenceOf(deleteAllCars, insertCars))
        // when
        val cars = carsController.cars()
        // then
        assertThat(cars, hasSize<Car>(3))
        assertThat(cars[0], equalTo(Car(1, "Audi")))
        assertThat(cars[1], equalTo(Car(2, "Opel")))
        assertThat(cars[2], equalTo(Car(3, "BMW")))
    }

    @Test
    fun itShouldSelectAllCarsWithRest() {
        // given
        prepareDatabase(sequenceOf(deleteAllCars))
        val audi = CarEntity.create(1, "Audi")
        val bmw = CarEntity.create(2, "VM")
        carsRepository.save(listOf(audi, bmw))

        // when
        val json = restTemplate.getForObject("/cars", String::class.java)

        // then
        assertThat(json, startsWith("[{\"id\":1"))
        assertThat(json, containsString("VM"))
    }

    private fun prepareDatabase(operation: Operation) {
        DbSetup(DataSourceDestination(dataSource), operation).launch()
    }
}
