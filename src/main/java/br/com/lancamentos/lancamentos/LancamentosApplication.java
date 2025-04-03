package br.com.lancamentos.lancamentos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class LancamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(LancamentosApplication.class, args);
	}

	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
				.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
	}

}
