package br.com.alex.ossystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.alex.ossystem.services.BDService;

@Configuration
@Profile("local")
public class LocalConfig {

	@Autowired
	private BDService bdService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddl;

	@Bean
	public boolean instanciaBD() {

		if (this.ddl.equals("create")) {
			this.bdService.instanciaBD();
		}
		return false;
	}

}
