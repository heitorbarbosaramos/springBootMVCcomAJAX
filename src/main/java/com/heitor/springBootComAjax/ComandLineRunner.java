package com.heitor.springBootComAjax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.heitor.springBootComAjax.domain.SocialMetaTag;
import com.heitor.springBootComAjax.service.SocialMetaTagService;

@Configuration
public class ComandLineRunner implements CommandLineRunner {

	@Autowired
	private SocialMetaTagService serviceMetaTag;
	
	private Logger LOG = LoggerFactory.getLogger("ComandLineRunner");
	
	@Override
	public void run(String... args) throws Exception {
		SocialMetaTag og = serviceMetaTag.getOpenGraphByUrl("https://www.udemy.com/course/curso-design-grafico-completo/");
		LOG.info(og.toString());
		SocialMetaTag twitter = serviceMetaTag.getTwitterCardByUrl("https://www.udemy.com/course/curso-design-grafico-completo/");
		LOG.info(twitter.toString());
		
		SocialMetaTag og2 = serviceMetaTag.getOpenGraphByUrl("https://www.pichau.com.br/perifericos/teclado/teclado-mecanico-pichau-p531-rgb-switch-outemu-blue-pg-p531ob-rgb");
		LOG.info(og2.toString());
		SocialMetaTag twitter2 = serviceMetaTag.getTwitterCardByUrl("https://www.pichau.com.br/perifericos/teclado/teclado-mecanico-pichau-p531-rgb-switch-outemu-blue-pg-p531ob-rgb");
		LOG.info(twitter2.toString());
	}

}
