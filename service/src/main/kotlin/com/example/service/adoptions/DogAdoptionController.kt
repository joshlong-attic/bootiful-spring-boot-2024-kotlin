package com.example.service.adoptions

import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.annotation.Id
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@Transactional
@ResponseBody
class DogAdoptionController(
    private val publisher: ApplicationEventPublisher,
    private val dogRepository: DogRepository
) {

    @PostMapping("/dogs/{dogId}/adoptions")
    fun adopt(
        @PathVariable dogId: Int,
        @RequestBody owner: Map<String, String>
    ) {

        this.dogRepository
            .findById(dogId).ifPresent {
                val newDog = this.dogRepository.save(
                    Dog(it.id, it.name, it.description, owner["name"])
                )
                this.publisher.publishEvent(DogAdoptionEvent(newDog.id))
                println("adopted ${newDog}")
            }


    }
}

interface DogRepository : ListCrudRepository<Dog, Int>

data class Dog(
    @Id val id: Int, val name: String,
    val description: String,
    val owner: String?
)