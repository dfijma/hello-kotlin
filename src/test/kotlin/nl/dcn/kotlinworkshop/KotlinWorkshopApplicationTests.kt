package nl.dcn.kotlinworkshop

import org.h2.server.Service
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootTest
class KotlinWorkshopApplicationTests(val jdbcTemplate: JdbcTemplate) {

	@Test
	fun contextLoads() {
	}


	@Test
	fun itWorks() {
		val s = MessageService(jdbcTemplate);
		val x = Message(null, "dit is de text");
		s.save(x);
		assert(x.id == null)
	}
}
