package pl.michalkowol.cities

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.michalkowol.NotFoundException
import java.util.*

@RestController
@EnableAutoConfiguration
@RequestMapping("/cities")
internal class CitiesController @Autowired constructor(private val citiesRepository: CitiesRepository) {

    @RequestMapping
    fun people(): Iterable<City> {
        return citiesRepository.findAll()
    }

    @RequestMapping("/{id}")
    fun findCityById(@PathVariable id: UUID): City {
        val city = citiesRepository.findOne(id) ?: throw NotFoundException("City with id=$id was not found.")
        return city
    }

    @RequestMapping("/add")
    fun addCity(@RequestParam("name", required = true) name: String): City {
        val city = City()
        city.name = name
        val savedCity = citiesRepository.save(city)
        return savedCity
    }

    @RequestMapping("/{id}/modify")
    fun modifyCity(@PathVariable id: UUID, @RequestParam("name", required = true) name: String): City {
        val city = citiesRepository.findOne(id) ?: throw NotFoundException("City with id=$id was not found.")
        city.name = name
        val savedCity = citiesRepository.save(city)
        return savedCity
    }
}
