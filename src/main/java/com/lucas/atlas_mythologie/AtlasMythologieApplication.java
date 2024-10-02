package com.lucas.atlas_mythologie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com/lucas/atlas_mythologie/model")
public class AtlasMythologieApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtlasMythologieApplication.class, args);
	}

}
