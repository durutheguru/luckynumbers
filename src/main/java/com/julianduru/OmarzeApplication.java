package com.julianduru;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@EntityScan(
	basePackages = {
		"com.julianduru"
	}
)
@EnableJpaRepositories(
	basePackages = {
		"com.julianduru"
	}
)
@EnableScheduling
@EnableSchedulerLock(
	defaultLockAtMostFor = "PT30S"
)
@EnableAspectJAutoProxy
@SpringBootApplication
public class OmarzeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmarzeApplication.class, args);
	}

}

