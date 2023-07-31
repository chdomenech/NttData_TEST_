package com.nttdata.costconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.nttdata.costconversion"
})
@ComponentScan(basePackages = {"com.nttdata.costconversion"})
@EnableJpaRepositories("com.nttdata.costconversion.domain.repository")   
@EntityScan("com.nttdata.costconversion.domain.model")
public class CostconversionApplication{

	public static void main(String[] args) {
		SpringApplication.run(CostconversionApplication.class, args);
	}

}
