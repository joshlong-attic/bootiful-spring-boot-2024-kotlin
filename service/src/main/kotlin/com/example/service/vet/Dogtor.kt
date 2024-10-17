package com.example.service.vet

import com.example.service.adoptions.DogAdoptionEvent
import org.springframework.modulith.events.ApplicationModuleListener
import org.springframework.stereotype.Service

@Service
class Dogtor {
    
    @ApplicationModuleListener
    fun checkup(dogAdoptedEvent: DogAdoptionEvent) {
        println("start: checking up on ${dogAdoptedEvent.dogId}")
        Thread.sleep(5_000)
        println("stop: checking up on ${dogAdoptedEvent.dogId}")
    }
}