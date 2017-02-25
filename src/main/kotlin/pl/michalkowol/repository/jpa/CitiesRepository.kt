package pl.michalkowol.repository.jpa

import org.springframework.data.repository.CrudRepository
import pl.michalkowol.model.jpa.City
import java.util.*

interface CitiesRepository : CrudRepository<City, UUID>
