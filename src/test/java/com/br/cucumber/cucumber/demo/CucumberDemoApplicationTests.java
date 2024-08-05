package com.br.cucumber.cucumber.demo;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@CucumberContextConfiguration
@ActiveProfiles("test")
class CucumberDemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
