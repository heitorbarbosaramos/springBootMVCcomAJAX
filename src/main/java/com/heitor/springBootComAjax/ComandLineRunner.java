package com.heitor.springBootComAjax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.heitor.springBootComAjax.service.SocialMetaTagService;

@Configuration
public class ComandLineRunner implements CommandLineRunner {

	@Autowired
	private SocialMetaTagService serviceMetaTag;
	
	@Override
	public void run(String... args) throws Exception {
		serviceMetaTag.getSocialMetaTagByUrl("https://www.udemy.com/course/curso-design-grafico-completo/");
		serviceMetaTag.getSocialMetaTagByUrl("https://www.pichau.com.br/perifericos/teclado/teclado-mecanico-pichau-p531-rgb-switch-outemu-blue-pg-p531ob-rgb");
	
	}

}
