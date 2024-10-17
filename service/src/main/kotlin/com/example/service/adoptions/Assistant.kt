package com.example.service.adoptions

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor
import org.springframework.ai.document.Document
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@RegisterReflectionForBinding(DogAdoptionSuggestion::class)
class Assistant {

    @Bean
    fun chatClient(
        cc: ChatClient.Builder,
        dr: DogRepository,
        vs: VectorStore
    ): ChatClient {

        if (false)
            dr.findAll().forEach {
                val dogument = Document("id: ${it.id}, name: ${it.name}, description: ${it.description}}")
                vs.add(listOf(dogument))
            }

        val system = """
            You are an AI powered assistant to help people adopt a dog from the adoption 
            agency named Pooch Palace with locations in Dubai, Seoul, Tokyo, Singapore, Paris, 
            Mumbai, New Delhi, Barcelona, San Francisco, and London. Information about the dogs available 
            will be presented below. If there is no information, then return a polite response suggesting we 
            don't have any dogs available.
        """.trimIndent()
        return cc
            .defaultAdvisors(QuestionAnswerAdvisor(vs))
            .defaultSystem(system)
            .build()
    }


    @Bean
    fun runner(cc: ChatClient) = ApplicationRunner {
        val content: DogAdoptionSuggestion = cc
            .prompt("do you have any neurotic dogs?")
            .call()
            .entity(DogAdoptionSuggestion::class.javaObjectType)
        println("the content is [$content]")


    }


}