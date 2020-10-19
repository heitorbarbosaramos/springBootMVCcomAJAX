package com.heitor.springBootComAjax.config;

import java.text.ParseException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.heitor.springBootComAjax.Banner;


@Configuration
@Profile("dev")
public class ProfileDEV {

	private final Logger LOG = org.slf4j.LoggerFactory.getLogger(ProfileDEV.class);
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	
	@Bean
	public boolean TesteConfiguration() throws ParseException {
		Banner.ambienteDesenvolvimento();
		LOG.info("AMBIENTE DE DESENVOLVIMENTO");
		LOG.info("STRATEGY BANCO : " + strategy);
		
		if(!"create".equals(strategy)) {
			return false;
		}
		
		return true;
		
	}
}
