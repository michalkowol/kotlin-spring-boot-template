package pl.michalkowol.cities

import org.springframework.data.repository.CrudRepository
import java.util.*

interface CitiesRepository : CrudRepository<City, UUID>
