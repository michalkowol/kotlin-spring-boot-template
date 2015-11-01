package pl.michalkowol

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.michalkowol.model.jpa.Person
import pl.michalkowol.repository.PeopleRepository

@RestController
@EnableAutoConfiguration
@RequestMapping("/people")
class PeopleController
@Autowired
constructor(private val peopleRepository: PeopleRepository) {

    protected val log = LoggerFactory.getLogger(javaClass)

    @RequestMapping
    fun people(): Iterable<Person> {
        val people = peopleRepository.findAll()
        people.map { person -> "Name ${person.name}" }.forEach { println(it) }
        return people
    }

    @RequestMapping(params = arrayOf("name"))
    fun peopleByName(@RequestParam name: String): Iterable<Person> {
        return peopleRepository.findByName(name)
    }

    @RequestMapping("/{id}")
    fun person(@PathVariable id: Long): Person? {
        if (id == 10L) {
            throw ArithmeticException(id.toString())
        } else if (id == 11L) {
            throw NameException(id.toString())
        } else if (id == 12L) {
            throw IllegalStateException(id.toString())
        }

        val person = peopleRepository.findOne(id)
        return person
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NameException::class)
    fun wrongName(ex: NameException): Exception {
        log.error("Wrong name: {}", ex.msg, ex)
        return ex
    }
}
