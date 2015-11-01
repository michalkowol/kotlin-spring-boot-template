package pl.michalkowol.model.jpa

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.*

//@Entity(name = "people")
//data class Person(@Id val id: Long?, val name: String?) {
//    protected constructor() : this(null, null)
//}

@Entity(name = "people")
class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = -1

    lateinit var name: String

    var age: Int = -1

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToMany
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = -1
    lateinit var street: String

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "addresses_people",
            joinColumns = arrayOf(JoinColumn(name = "address_id", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "person_id", referencedColumnName = "id"))
    )
    lateinit var people: MutableList<Person>
}