package com.example.service

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.modulith.core.ApplicationModules
import org.springframework.modulith.docs.Documenter

@SpringBootTest
class ServiceApplicationTests {

	@Test
	fun contextLoads() {
		val am = ApplicationModules.of(ServiceApplication::class.java)
		am.verify()
		
		println(am )
		
		Documenter(am).writeDocumentation()
	}

}
