package com.example.bookmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.bookmanagement.repository")
public class BookManagementApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BookManagementApplication.class, args);
	}

}
