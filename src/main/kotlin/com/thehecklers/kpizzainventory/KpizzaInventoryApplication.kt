package com.thehecklers.kpizzainventory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import java.util.function.Consumer

@SpringBootApplication
class KpizzaInventoryApplication {
    @Bean
    fun saveOrder(repo: PizzaOrderRepo) = Consumer<Flux<Pizza>> {
        it.subscribe { println("    >>> Order logged: ${repo.save(it).block()} <<<   ") }
    }
}

fun main(args: Array<String>) {
    runApplication<KpizzaInventoryApplication>(*args)
}

interface PizzaOrderRepo : ReactiveCrudRepository<Pizza, Long>

@Document
data class Pizza(@Id val id: Long, val description: String)
