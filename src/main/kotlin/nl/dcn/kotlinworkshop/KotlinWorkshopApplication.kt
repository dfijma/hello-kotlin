package nl.dcn.kotlinworkshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class KotlinWorkshopApplication

data class Message(val id: String?, val text: String)


@Service
class MessageService(val db: JdbcTemplate) {
	fun findMessages(): List<Message> = db.query("select * from messages") { response, _ ->
		Message(response.getString("id"), response.getString("text"))
	}

	fun save(message: Message){
		db.update("insert into messages values ( ?, ? )",
			message.id, message.text)
	}
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
