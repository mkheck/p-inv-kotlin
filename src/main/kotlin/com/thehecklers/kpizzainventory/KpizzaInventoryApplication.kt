package com.thehecklers.kpizzainventory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.repository.CrudRepository
import java.util.function.Consumer

@SpringBootApplication
class KpizzaInventoryApplication {
    @Bean
    fun saveOrder(repo: PizzaOrderRepo) = Consumer<Pizza> {
        println("Order logged : ${repo.save(it)}")
    }
}

fun main(args: Array<String>) {
    runApplication<KpizzaInventoryApplication>(*args)
}

interface PizzaOrderRepo : CrudRepository<Pizza, Long>

@Document
data class Pizza(@Id val id: Long, val description: String)
