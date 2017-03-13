package com.michalkowol.cars

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "cars")
class CarEntity {

    @Id
    var id: Int = -1

    lateinit var name: String

    companion object {
        fun create(id: Int, name: String): CarEntity {
            val carEntity = CarEntity()
            carEntity.id = id
            carEntity.name = name
            return carEntity
        }
    }
}
