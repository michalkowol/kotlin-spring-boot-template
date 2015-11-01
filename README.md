# Spring Boot Template

Spring Boot template with Kotlin, PostgreSQL 9.4, JPA, CrudRepository.

[http://localhost:8080/people](http://localhost:8080/people)

[http://localhost:8080/people/1](http://localhost:8080/people/1)

## Build

```
gradle build
```

## Run

```
gradle run
# or
gradle bootRun
```

## One-Jar

```
gradle assemble
java -jar ${NAME}-${VERSION}.jar
```

## References
* [Spring Boot](http://projects.spring.io/spring-boot/)
* [Use Jetty instead of Tomcat](http://docs.spring.io/spring-boot/docs/current/reference/html/howto-embedded-servlet-containers.html#howto-use-jetty-instead-of-tomcat)
* [AssertionFailure: Fail to process type argument in a generic declaration](https://hibernate.atlassian.net/browse/HHH-9403)
* [Covariant types in Kotlin translated to wildcard types in Java](https://youtrack.jetbrains.com/issue/KT-5792)
* [Kotlin with JPA: default constructor hell](http://stackoverflow.com/questions/32038177/kotlin-with-jpa-default-constructor-hell)