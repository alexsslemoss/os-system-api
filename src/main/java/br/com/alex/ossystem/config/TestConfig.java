package br.com.alex.ossystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.alex.ossystem.services.BDService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private BDService deBdService;

	@Bean
	public void instanciaBD() {
		
		this.deBdService.instanciaBD();
		
	}

}
