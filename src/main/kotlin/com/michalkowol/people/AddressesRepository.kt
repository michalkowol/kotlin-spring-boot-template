package com.michalkowol.people

import org.springframework.data.repository.CrudRepository
import java.util.*

interface AddressesRepository : CrudRepository<Address, UUID>
