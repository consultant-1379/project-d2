package com.ericsson.graduate.group2.CIstabilityproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration
public class CiStabilityProjectApplication {

//	@Autowired
//	private TestJobDb testJobDb;

//	@Autowired
//	private TestBuildDb testBuildDb;

	public static void main(String[] args) {
		SpringApplication.run(CiStabilityProjectApplication.class, args);
	}

}
