package com.heitor.springBootComAjax.config;

import java.text.ParseException;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.heitor.springBootComAjax.Banner;



@Configuration
@Profile("test")
public class ProfileTES {

	private final Logger LOG = org.slf4j.LoggerFactory.getLogger(ProfileTES.class);

	@Bean
	public boolean TesteConfiguration() throws ParseException {
		Banner.ambienteTeste();
		LOG.info("AMBIANTE DE TESTE");
		
		return true;
		

	}
}
