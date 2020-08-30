package com.julianduru.omarze;

import com.julianduru.omarze.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
public class OmarzeApplicationTests {

	@Test
	public void contextLoads() {}

}
