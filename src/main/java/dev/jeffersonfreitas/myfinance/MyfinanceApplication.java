package dev.jeffersonfreitas.myfinance;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyfinanceApplication {
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}	

	public static void main(String[] args) {
		SpringApplication.run(MyfinanceApplication.class, args);
	}

}
