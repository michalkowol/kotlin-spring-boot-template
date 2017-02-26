# Spring Boot Template

Spring Boot template with Kotlin, PostgreSQL 9.4, JPA, CrudRepository.

[http://localhost:8080/people](http://localhost:8080/people)

[http://localhost:8080/people/1](http://localhost:8080/people/1)

## Build

```bash
gradle build
```

## Run

```bash
gradle bootRun
```

## Test

```bash
gradle test
```

## Start db

```bash
docker run --name softwareberg-postgres-db -p 5432:5432 -e POSTGRES_USER=softwareberg -e POSTGRES_PASSWORD=softwareberg -d postgres:9.6
```

## Clean db

```bash
flyway -url=jdbc:postgresql://localhost:5432/softwareberg -user=softwareberg -password=softwareberg clean
```

## One-Jar

```bash
gradle assemble
java -jar build/libs/{NAME}-${VERSION}.jar
```

## Random

```kotlin
package pl.michalkowol.repository.simple

import org.springframework.stereotype.Repository

/*
| Annotation | Meaning                                             |
+------------+-----------------------------------------------------+
| @Component | generic stereotype for any Spring-managed component |
| @Repository| stereotype for persistence layer                    |
| @Service   | stereotype for service layer                        |
| @Controller| stereotype for presentation layer (spring-mvc)      |
 */

data class Address(val street: String, val city: String)
data class Person(val name: String, val addresses: List<Address>)

@Repository
open class SimplePeopleRepository {
    /*
    Why does it have `open` modifier?
    Unable to proxy method [public final java.util.List pl.michalkowol.repository.simple.SimplePeopleRepository.findByName(java.lang.String)]
    because it is final: All calls to this method via a proxy will NOT be routed to the target instance.
     */
    open fun findOne(id: Long): Person? {
        if (id == 1L) {
            return Person("Michal", listOf(Address("Chemiczna", "Gliwice"), Address("Pulawska", "Warszawa")))
        } else if (id == 2L) {
            return Person("Kasia", listOf(Address("Przybylskiego", "Warszawa")))
        }
        return null
    }

    open fun findAll(): List<Person> {
        val people = listOf(findOne(1), findOne(2)).filterNotNull()
        return people
    }

    open fun findByName(name: String): List<Person> {
        val people = listOf(findOne(1), findOne(2)).filterNotNull()
        return people.filter { it.name == name }
    }
}
```

## References
* [Spring Boot](http://projects.spring.io/spring-boot/)
* [Use Jetty instead of Tomcat](http://docs.spring.io/spring-boot/docs/current/reference/html/howto-embedded-servlet-containers.html#howto-use-jetty-instead-of-tomcat)
* [AssertionFailure: Fail to process type argument in a generic declaration](https://hibernate.atlassian.net/browse/HHH-9403)
* [Covariant types in Kotlin translated to wildcard types in Java](https://youtrack.jetbrains.com/issue/KT-5792)
* [Kotlin with JPA: default constructor hell](http://stackoverflow.com/questions/32038177/kotlin-with-jpa-default-constructor-hell)
* [CrudRepository](http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html)
