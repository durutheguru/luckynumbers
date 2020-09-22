package com.julianduru.omarze;

import com.julianduru.omarze.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = {TestConfig.class})
public class OmarzeApplicationTests {

	@Test
	public void contextLoads() {}

}
