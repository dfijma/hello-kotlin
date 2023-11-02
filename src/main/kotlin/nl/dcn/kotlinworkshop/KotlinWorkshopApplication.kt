package nl.dcn.kotlinworkshop

import nl.dcn.kotlinworkshop.service.DataParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.data.repository.CrudRepository
import java.util.*

@SpringBootApplication
class KotlinWorkshopApplication

@Table("MESSAGES")
data class Message(@Id var id: String?, val text: String)

interface MessageRepository : CrudRepository<Message, String>

@Service
class MessageService(val repository: MessageRepository) {

	@Autowired
	lateinit var dataParser: DataParser

	fun findMessages(): List<Message> = repository.findAll().toList();

	fun save(message: Message) {
		repository.save(message)
	}


	fun <T : Any> Optional<out T>.toList(): List<T> =
		if (isPresent) listOf(get()) else emptyList()
}

@RestController
class MessageController(val service: MessageService) {
	@GetMapping("/")
	fun index(): List<Message> = service.findMessages()

	@PostMapping("/")
	fun post(@RequestBody message: Message) {
		service.save(message)
	}
}

fun main(args: Array<String>) {
	runApplication<KotlinWorkshopApplication>(*args)
}
