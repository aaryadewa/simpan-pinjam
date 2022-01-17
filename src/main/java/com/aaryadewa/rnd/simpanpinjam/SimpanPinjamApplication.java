package com.aaryadewa.rnd.simpanpinjam;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpanPinjamApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SimpanPinjamApplication.class);
		Map<String, Object> defaultProperties = new HashMap<>(1);

		defaultProperties.put("spring.profiles.default", "dev");
		app.setDefaultProperties(defaultProperties);
		app.run(args);
	}

}
