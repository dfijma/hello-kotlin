package nl.dcn.kotlinworkshop

import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.h2.server.Service
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootTest
class KotlinWorkshopApplicationTests() {

	@Test
	fun contextLoads() {
	}

	@Test
	fun itWorks() {

	}
}

class DataTest() {
	@Test
	fun aBasicUnitTest() {
		val x = Message(null, "dit is de text")
		assert(x.id == null)
		assert(x.text.equals("dit is de text"))
	}
}

class ServiceTest() {

	val jdbcTemplate: JdbcTemplate = mockk();
	val messageService: MessageService = MessageService(jdbcTemplate);

	@Test
	fun okItDoesNotTestShitButItWorksAgainstAMock() {
		every { jdbcTemplate.update(any<String>(), "1", "2") } returns 1;
		val message = Message("1", "2")
		messageService.save(message)
		verify { jdbcTemplate.update("insert into messages values ( ?, ? )", "1", "2") }
	}

}

@DataJpaTest
class ServiceIntegrationTest() {

	@Autowired
	lateinit var jdbcTemplate: JdbcTemplate

	@Test
	fun againThisIntegrationTestDoesNotTestMuchButItRunsAgainstARealJbdcTemplate() {
		val message = Message("1", "2")
		val messageService = MessageService(jdbcTemplate)
		messageService.save(message);
		val resuts = messageService.findMessages();
		assert( resuts.size == 1)
	}
}
