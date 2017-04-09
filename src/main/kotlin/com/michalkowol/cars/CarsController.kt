package com.michalkowol.cars

import com.michalkowol.BadRequestException
import com.michalkowol.NotFoundException
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.*
import javax.inject.Inject

@RestController
@EnableAutoConfiguration
@RequestMapping(path = arrayOf("/cars"), produces = arrayOf("application/json"))
class CarsController @Inject constructor(private val carsRepository: CarsRepository) {

    @RequestMapping
    fun cars(): List<Car> {
        return carsRepository.findAll().map { carEntity -> Car(carEntity.id, carEntity.name) }
    }

    @RequestMapping("/{id}")
    fun carById(@PathVariable id: Int): Car {
        val carEntity = carsRepository.findOne(id).orElseThrow { NotFoundException("Car with id=$id not found") }
        val car = Car(carEntity.id, carEntity.name)
        return car
    }

    @RequestMapping(method = arrayOf(POST))
    @ResponseStatus(CREATED)
    fun createCar(@RequestBody car: Car): Car {
        val carEntity = CarEntity.create(car.id, car.name)
        val saved = carsRepository.save(carEntity)
        return Car(saved.id, saved.name)
    }

    @RequestMapping("/create")
    @ResponseStatus(CREATED)
    fun createCarWithQueryParams(@RequestParam id: Int, @RequestParam name: String): Car {
        // not RESTfull - only for demo
        val carEntity = CarEntity.create(id, name)
        val saved = carsRepository.save(carEntity)
        return Car(saved.id, saved.name)
    }

    @RequestMapping(path = arrayOf("/{id}"), method = arrayOf(PUT))
    fun changeCarName(@PathVariable("id") id: Int, @RequestBody car: Car): Car {
        if (car.id != id) {
            throw BadRequestException("Request id is not equal Car.id")
        }

        val carEntity = carsRepository.findOne(id).orElseThrow { NotFoundException("Car with id=$id not found") }
        carEntity.name = car.name
        val saved = carsRepository.save(carEntity)

        return Car(saved.id, saved.name)
    }

    @RequestMapping(path = arrayOf("/{id}"), method = arrayOf(DELETE))
    @ResponseStatus(NO_CONTENT)
    private fun deleteCar(@PathVariable("id") id: Int) {
        carsRepository.delete(id)
    }
}
