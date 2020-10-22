package com.heitor.springBootComAjax.config;

import java.text.ParseException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.heitor.springBootComAjax.Banner;
import com.heitor.springBootComAjax.service.SocialMetaTagService;



@Configuration
@Profile("test")
public class ProfileTES {

	private final Logger LOG = org.slf4j.LoggerFactory.getLogger(ProfileTES.class);

	@Autowired
	private DBServiceInstanciamento dbservice;
	@Autowired
	private SocialMetaTagService serviceMetaTag;
	
	@Bean
	public boolean TesteConfiguration() throws ParseException {
		Banner.ambienteTeste();
		LOG.info("AMBIANTE DE TESTE");
		
		dbservice.instantiationDataBasa();
		
		serviceMetaTag.getSocialMetaTagByUrl("https://www.udemy.com/course/curso-design-grafico-completo/");
		serviceMetaTag.getSocialMetaTagByUrl("https://www.pichau.com.br/perifericos/teclado/teclado-mecanico-pichau-p531-rgb-switch-outemu-blue-pg-p531ob-rgb");

		
		
		
		
		
		return true;
		

	}
}
