package pl.michalkowol.model.simple

data class Address(val street: String, val city: String)
data class Person(val name: String, val addresses: List<Address>)