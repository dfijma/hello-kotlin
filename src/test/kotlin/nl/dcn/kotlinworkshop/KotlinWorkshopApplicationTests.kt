package nl.dcn.kotlinworkshop

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


class DataUnitTest() {
	@Test
	fun aBasicUnitTest() {
		val x = Message(null, "dit is de text")
		assert(x.id == null)
		assert(x.text.equals("dit is de text"))
	}
}

class ServiceUnitTest() {

	// val jdbcTemplate: JdbcTemplate = mockk();
	val messageRepository: MessageRepository = mockk()
	val messageService: MessageService = MessageService(messageRepository);

	@Test
	fun okItDoesNotTestShitButItWorksAgainstAMock() {
		every { messageRepository.save(any()) } returns Message("1", "2")
		val message = Message(null, "2")
		messageService.save(message)
		verify { messageRepository.save(Message(null ,"2")) }
	}

}

@WebMvcTest
class ControllerUnitTest(@Autowired val mockMvc: MockMvc) {
	@MockkBean
	lateinit var messageService: MessageService;

	@Test
	fun itWorks() {
		every { messageService.findMessages() } returns emptyList();
		mockMvc.perform(get("/")).andExpect(status().isOk)
	}
}

@DataJpaTest
class SomeIntegrationTestAgainstARealJdbcTemplate() {
	@Autowired
	lateinit var jdbcTemplate: JdbcTemplate

	@Test
	fun againThisIntegrationTestDoesNotTestMuchButItRunsAgainstARealJbdcTemplate() {

		val results = jdbcTemplate.query("select * from messages") { response, _ ->
			Message(response.getString("id"), response.getString("text"))
		}
		assert( results.size == 0)

	}
}

@SpringBootTest
class ServiceIntegrationTest() {

	@Autowired
	lateinit var messageRepository: MessageRepository;

	@Test
	fun againThisIntegrationTestDoesNotTestMuchButItRunsAgainstARealJbdcTemplate() {
		val message = Message(null, "2")
		val messageService = MessageService(messageRepository)
		messageService.save(message)
		val results = messageService.findMessages()
		assert( results.size == 1)
	}
}


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KotlinWorkshopApplicationTests() {

	@Autowired
	lateinit var restTemplate: TestRestTemplate

	@Test
	fun contextLoads() {
	}

	@Test
	fun itWorks() {
		val message = Message(null, "Henk de Paashaas")
		val postResult = restTemplate.postForEntity("/", message, Object::class.java)
		val result = restTemplate.getForEntity("/", Array<Message>::class.java);
		assert(result.hasBody())
		assert(result.body!!.get(0).text == "Henk de Paashaas")
	}
}
