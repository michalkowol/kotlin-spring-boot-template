package com.michalkowol.cars

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test

class CarsControllerSpec {

    @Test
    fun itShouldFindAllCars() {
        // given
        val carsRepository = mock<CarsRepository> {
            on { findAll() } doReturn listOf(CarEntity.create(1, "Audi"), CarEntity.create(2, "Ford"))
        }
        val carsController = CarsController(carsRepository)
        // when
        val cars = carsController.cars()
        // then
//        assertThat(cars, hasSize(equalTo(2)))
        assertThat(cars.size, equalTo(2))
        assertThat(cars[0], equalTo(Car(1, "Audi")))
    }
}
