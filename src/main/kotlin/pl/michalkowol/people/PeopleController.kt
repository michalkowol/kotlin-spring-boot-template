package pl.michalkowol.people

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.michalkowol.NotFoundException
import java.util.*

@RestController
@EnableAutoConfiguration
@RequestMapping("/people")
internal class PeopleController @Autowired constructor(private val peopleRepository: PeopleRepository) {

    private val log = LoggerFactory.getLogger(PeopleController::class.java)

    @RequestMapping
    fun people(): Iterable<Person> {
        val people = peopleRepository.findAll()
        people.map { person -> "Name ${person.name}" }.forEach(::println)
        return people
    }

    @RequestMapping(params = arrayOf("name"))
    fun peopleByName(@RequestParam name: String): Iterable<Person> {
        return peopleRepository.findByName(name)
    }

    @RequestMapping("/{id}")
    fun person(@PathVariable id: UUID): Person {
        if (id == UUID.fromString("3a0dc871-ea31-47e7-b0f5-398a863b8629")) {
            throw ArithmeticException(id.toString())
        } else if (id == UUID.fromString("5d2a3e28-e1f5-4f5f-adc3-5552d2fa30b0")) {
            throw NameException(id.toString())
        } else if (id == UUID.fromString("b04b8e54-53df-4558-b18b-8de44e9fc3c1")) {
            throw IllegalStateException(id.toString())
        }

        val person = peopleRepository.findOne(id) ?: throw NotFoundException("Person with id=$id was not found.")
        return person
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NameException::class)
    fun wrongName(ex: NameException): Exception {
        log.error("Wrong name: {}", ex.msg, ex)
        return ex
    }
}
