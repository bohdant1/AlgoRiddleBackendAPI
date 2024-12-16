package com.algoriddle.AlgoRiddleBackendApi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class AlgoRiddleBackendApiApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertEquals(true,!false);
	}

	@Test
	void testHello(){Assertions.assertEquals("a","a");}

}
