package com.SpringSecurityUpdated;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.SpringSecurityUpdated"})
public class SpringSecurityUpdatedApplication {

	public static void main(String[] args) {

		// ------IMPORTANT------
		// "UserDetails" & "UserDetailsService" are used for authentication or Authorization purposes,
		// We use "UserDetails" for implementing String "getPassword();, String getUsername();"
		// We use "UserDetailsService" for implementing "loadUserByUsername(String username);"

		SpringApplication.run(SpringSecurityUpdatedApplication.class, args);

	}

}



