package com.fldsmdfr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.fldsmdfr")
public class PhysiotherapyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhysiotherapyApiApplication.class, args);
	}

}
