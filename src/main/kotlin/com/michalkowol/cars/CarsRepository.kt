package com.michalkowol.cars

import org.springframework.data.repository.CrudRepository

interface CarsRepository : CrudRepository<CarEntity, Int>
