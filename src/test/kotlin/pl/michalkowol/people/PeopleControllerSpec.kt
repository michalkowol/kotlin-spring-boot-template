package pl.michalkowol.people

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import java.util.*

class PeopleControllerSpec {

    @Test
    fun `it should find person by id`() {
        // given
        val uuid = UUID.fromString("deadbeef-dead-beef-dead-beef00000001")
        val peopleRepository = mock<PeopleRepository> {
            on { findOne(any()) } doReturn Person.create("Michal", 28, emptyList())
        }
        val peopleController = PeopleController(peopleRepository)
        // when
        val michal = peopleController.person(uuid)
        // then
        assertEquals("Michal", michal.name)
    }

    @Test(expected = ArithmeticException::class)
    fun `it should throw "ArithmeticException"`() {
        // given
        val uuid = UUID.fromString("3a0dc871-ea31-47e7-b0f5-398a863b8629")
        val peopleRepository = mock<PeopleRepository>()
        val peopleController = PeopleController(peopleRepository)
        try {
            // when
            peopleController.person(uuid)
        } catch (e: ArithmeticException) {
            // then
            assertEquals("3a0dc871-ea31-47e7-b0f5-398a863b8629", e.message)
            throw e
        }
        fail()
    }

    @Test(expected = NameException::class)
    fun `it should throw "NameException"`() {
        // given
        val uuid = UUID.fromString("5d2a3e28-e1f5-4f5f-adc3-5552d2fa30b0")
        val peopleRepository = mock<PeopleRepository>()
        val peopleController = PeopleController(peopleRepository)
        try {
            // when
            peopleController.person(uuid)
        } catch (e: NameException) {
            // then
            assertEquals("5d2a3e28-e1f5-4f5f-adc3-5552d2fa30b0", e.message)
            throw e
        }
        fail()
    }

    @Test(expected = IllegalStateException::class)
    fun `it should throw "IllegalStateException"`() {
        // given
        val uuid = UUID.fromString("b04b8e54-53df-4558-b18b-8de44e9fc3c1")
        val peopleRepository = mock<PeopleRepository>()
        val peopleController = PeopleController(peopleRepository)
        try {
            // when
            peopleController.person(uuid)
        } catch (e: IllegalStateException) {
            // then
            assertEquals("b04b8e54-53df-4558-b18b-8de44e9fc3c1", e.message)
            throw e
        }
        fail()
    }
}
