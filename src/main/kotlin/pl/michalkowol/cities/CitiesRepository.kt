package pl.michalkowol.cities

import org.springframework.data.repository.CrudRepository
import java.util.*

internal interface CitiesRepository : CrudRepository<City, UUID>
