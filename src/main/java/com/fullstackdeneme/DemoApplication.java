package com.fullstackdeneme;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
/*import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;*/

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {SpringApplication.run(DemoApplication.class, args);}

	/**
	 * Encode the password
	 * @return
	 */
	/*@Bean
	public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();
	}*/

	/**
	 * ModelMapper forResponse and forRequests
	 * @return
	 */
	@Bean
	public ModelMapper getModelMapper(){return new ModelMapper();
	}
}
