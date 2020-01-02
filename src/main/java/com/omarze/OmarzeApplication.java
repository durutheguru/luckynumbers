package com.omarze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class OmarzeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmarzeApplication.class, args);
	}

}
