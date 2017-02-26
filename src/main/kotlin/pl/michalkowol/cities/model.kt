package pl.michalkowol.cities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "cities")
class City {

    companion object {
        fun create(name: String): City {
            val city = City()
            city.name = name
            return city
        }
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    var id: UUID? = null

//    @Column(name = "name")
    lateinit var name: String
}
