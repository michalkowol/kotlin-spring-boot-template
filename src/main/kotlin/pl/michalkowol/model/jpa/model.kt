package pl.michalkowol.model.jpa

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*
import javax.persistence.*

@Entity(name = "people")
class Person {
    @Id
    lateinit var id: UUID

    lateinit var name: String

    var age: Int = -1

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "addresses_people",
            joinColumns = arrayOf(JoinColumn(name = "person_id", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "address_id", referencedColumnName = "id"))
    )
    lateinit var addresses: MutableList<Address>
}

@Entity(name = "addresses")
class Address {
    @Id
    lateinit var id: UUID

    lateinit var street: String

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    lateinit var city: City

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "addresses_people",
            joinColumns = arrayOf(JoinColumn(name = "address_id", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "person_id", referencedColumnName = "id"))
    )
    lateinit var people: MutableList<Person>
}

@Entity(name = "cities")
class City {
    @Id
    lateinit var id: UUID

    lateinit var name: String
}
