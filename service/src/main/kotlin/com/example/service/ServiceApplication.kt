package com.example.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@SpringBootApplication
class ServiceApplication {

    @Bean
    fun http(rc: RestClient.Builder) = rc.build()

}

fun main(args: Array<String>) {
    runApplication<ServiceApplication>(*args)
}


// @CoraIberkleid 
// Cora Iberkleid

@Controller
@ResponseBody
class ScaleDemoController(private val http: RestClient) {

    @GetMapping("/delay")
    fun delay() = this.http
        .get()
        .uri("https://httpbin.org/delay/4")
        .retrieve()
        .body<String>()
    

}