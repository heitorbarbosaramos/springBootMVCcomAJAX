package com.heitor.springBootComAjax.config;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.heitor.springBootComAjax.Banner;
import com.heitor.springBootComAjax.domain.Promocao;
import com.heitor.springBootComAjax.domain.SocialMetaTag;
import com.heitor.springBootComAjax.repository.CategoriaRepository;
import com.heitor.springBootComAjax.repository.PromocaoRepository;
import com.heitor.springBootComAjax.service.SocialMetaTagService;



@Configuration
@Profile("test")
public class ProfileTES {

	private final Logger LOG = org.slf4j.LoggerFactory.getLogger(ProfileTES.class);

	@Autowired
	private DBServiceInstanciamento dbservice;
	@Autowired
	private SocialMetaTagService serviceMetaTag;
	@Autowired
	private CategoriaRepository repoCategoria;
	@Autowired
	private  PromocaoRepository repoPromocao;
	
	@Bean
	public boolean TesteConfiguration() throws ParseException {
		Banner.ambienteTeste();
		LOG.info("AMBIANTE DE TESTE");
		
		dbservice.instantiationDataBasa();
		
		SocialMetaTag tag1 = serviceMetaTag.getSocialMetaTagByUrl("https://www.udemy.com/course/curso-design-grafico-completo/");
		SocialMetaTag tag2 = serviceMetaTag.getSocialMetaTagByUrl("https://www.mobly.com.br/sofa-3-lugares-retratil-lubeck-plush-suede-grafite-769984.html?spall_source=especiais&gclid=Cj0KCQjwit_8BRCoARIsAIx3Rj6L6mkVHRMQsK6ZHeRMHA3vtlRNNtrpCsDUPuGpCYmbifeKEJErtysaAjASEALw_wcB");
		SocialMetaTag tag3 = serviceMetaTag.getSocialMetaTagByUrl("https://www.udemy.com/course/web-completo/");
		SocialMetaTag tag4 = serviceMetaTag.getSocialMetaTagByUrl("https://www.udemy.com/course/criacao-de-sites-com-wordpress/");
		SocialMetaTag tag5 = serviceMetaTag.getSocialMetaTagByUrl("https://www.udemy.com/course/design-de-interface/");
		SocialMetaTag tag6 = serviceMetaTag.getSocialMetaTagByUrl("https://www.udemy.com/course/controle-de-vendas-java/");
		SocialMetaTag tag7 = serviceMetaTag.getSocialMetaTagByUrl("https://www.udemy.com/course/react-redux-pt/");
		SocialMetaTag tag8 = serviceMetaTag.getSocialMetaTagByUrl("https://www.udemy.com/course/fundamentos-de-programacao-com-java/");
		SocialMetaTag tag9 = serviceMetaTag.getSocialMetaTagByUrl("https://www.udemy.com/course/curso-web/");
		SocialMetaTag tag10 = serviceMetaTag.getSocialMetaTagByUrl("https://www.udemy.com/course/curso-react-native/");
		SocialMetaTag tag11 = serviceMetaTag.getSocialMetaTagByUrl("https://www.udemy.com/course/php-7-completo/");
		SocialMetaTag tag12 = serviceMetaTag.getSocialMetaTagByUrl("https://www.udemy.com/course/testes-unitarios-em-java/");

		Promocao promo1 = new Promocao();
		promo1.setId(null);
		promo1.setTitulo(tag1.getTitle());
		promo1.setLinkPromocao(tag1.getUrl());
		promo1.setSite(tag1.getSite());
		promo1.setDescricao("");
		promo1.setLinkImagem(tag1.getImagem());
		promo1.setPreco(BigDecimal.valueOf(29.00));
		promo1.setLikes(0);
		promo1.setDataCadastro(LocalDate.now());
		promo1.setCategoria(repoCategoria.findById(2l).orElse(null));
		
		Promocao promo2 = new Promocao();
		promo2.setId(null);
		promo2.setTitulo(tag2.getTitle());
		promo2.setLinkPromocao(tag2.getUrl());
		promo2.setSite(tag2.getSite());
		promo2.setDescricao("");
		promo2.setLinkImagem(tag2.getImagem());
		promo2.setPreco(BigDecimal.valueOf(29.00));
		promo2.setLikes(0);
		promo2.setDataCadastro(LocalDate.now());
		promo2.setCategoria(repoCategoria.findById(2l).orElse(null));
		
		Promocao promo3 = new Promocao();
		promo3.setId(null);
		promo3.setTitulo(tag3.getTitle());
		promo3.setLinkPromocao(tag3.getUrl());
		promo3.setSite(tag3.getSite());
		promo3.setDescricao("");
		promo3.setLinkImagem(tag3.getImagem());
		promo3.setPreco(BigDecimal.valueOf(29.00));
		promo3.setLikes(0);
		promo3.setDataCadastro(LocalDate.now());
		promo3.setCategoria(repoCategoria.findById(2l).orElse(null));
		
		Promocao promo4 = new Promocao();
		promo4.setId(null);
		promo4.setTitulo(tag4.getTitle());
		promo4.setLinkPromocao(tag4.getUrl());
		promo4.setSite(tag4.getSite());
		promo4.setDescricao("");
		promo4.setLinkImagem(tag4.getImagem());
		promo4.setPreco(BigDecimal.valueOf(29.00));
		promo4.setLikes(0);
		promo4.setDataCadastro(LocalDate.now());
		promo4.setCategoria(repoCategoria.findById(2l).orElse(null));
		
		Promocao promo5 = new Promocao();
		promo5.setId(null);
		promo5.setTitulo(tag5.getTitle());
		promo5.setLinkPromocao(tag5.getUrl());
		promo5.setSite(tag5.getSite());
		promo5.setDescricao("");
		promo5.setLinkImagem(tag5.getImagem());
		promo5.setPreco(BigDecimal.valueOf(29.00));
		promo5.setLikes(0);
		promo5.setDataCadastro(LocalDate.now());
		promo5.setCategoria(repoCategoria.findById(2l).orElse(null));
		
		Promocao promo6 = new Promocao();
		promo6.setId(null);
		promo6.setTitulo(tag6.getTitle());
		promo6.setLinkPromocao(tag6.getUrl());
		promo6.setSite(tag6.getSite());
		promo6.setDescricao("");
		promo6.setLinkImagem(tag6.getImagem());
		promo6.setPreco(BigDecimal.valueOf(29.00));
		promo6.setLikes(0);
		promo6.setDataCadastro(LocalDate.now());
		promo6.setCategoria(repoCategoria.findById(2l).orElse(null));
		
		Promocao promo7 = new Promocao();
		promo7.setId(null);
		promo7.setTitulo(tag7.getTitle());
		promo7.setLinkPromocao(tag7.getUrl());
		promo7.setSite(tag7.getSite());
		promo7.setDescricao("");
		promo7.setLinkImagem(tag7.getImagem());
		promo7.setPreco(BigDecimal.valueOf(29.00));
		promo7.setLikes(0);
		promo7.setDataCadastro(LocalDate.now());
		promo7.setCategoria(repoCategoria.findById(2l).orElse(null));
		
		Promocao promo8 = new Promocao();
		promo8.setId(null);
		promo8.setTitulo(tag8.getTitle());
		promo8.setLinkPromocao(tag8.getUrl());
		promo8.setSite(tag8.getSite());
		promo8.setDescricao("");
		promo8.setLinkImagem(tag8.getImagem());
		promo8.setPreco(BigDecimal.valueOf(29.00));
		promo8.setLikes(0);
		promo8.setDataCadastro(LocalDate.now());
		promo8.setCategoria(repoCategoria.findById(2l).orElse(null));
		
		Promocao promo9 = new Promocao();
		promo9.setId(null);
		promo9.setTitulo(tag9.getTitle());
		promo9.setLinkPromocao(tag9.getUrl());
		promo9.setSite(tag9.getSite());
		promo9.setDescricao("");
		promo9.setLinkImagem(tag2.getImagem());
		promo9.setPreco(BigDecimal.valueOf(29.00));
		promo9.setLikes(0);
		promo9.setDataCadastro(LocalDate.now());
		promo9.setCategoria(repoCategoria.findById(2l).orElse(null));
		
		Promocao promo10 = new Promocao();
		promo10.setId(null);
		promo10.setTitulo(tag10.getTitle());
		promo10.setLinkPromocao(tag10.getUrl());
		promo10.setSite(tag10.getSite());
		promo10.setDescricao("");
		promo10.setLinkImagem(tag10.getImagem());
		promo10.setPreco(BigDecimal.valueOf(29.00));
		promo10.setLikes(0);
		promo10.setDataCadastro(LocalDate.now());
		promo10.setCategoria(repoCategoria.findById(2l).orElse(null));
		
		Promocao promo11 = new Promocao();
		promo11.setId(null);
		promo11.setTitulo(tag11.getTitle());
		promo11.setLinkPromocao(tag11.getUrl());
		promo11.setSite(tag11.getSite());
		promo11.setDescricao("");
		promo11.setLinkImagem(tag11.getImagem());
		promo11.setPreco(BigDecimal.valueOf(29.00));
		promo11.setLikes(0);
		promo11.setDataCadastro(LocalDate.now());
		promo11.setCategoria(repoCategoria.findById(2l).orElse(null));
		
		Promocao promo12 = new Promocao();
		promo12.setId(null);
		promo12.setTitulo(tag12.getTitle());
		promo12.setLinkPromocao(tag12.getUrl());
		promo12.setSite(tag12.getSite());
		promo12.setDescricao("");
		promo12.setLinkImagem(tag12.getImagem());
		promo12.setPreco(BigDecimal.valueOf(29.00));
		promo12.setLikes(0);
		promo12.setDataCadastro(LocalDate.now());
		promo12.setCategoria(repoCategoria.findById(2l).orElse(null));
		
		repoPromocao.saveAll(Arrays.asList(promo1, promo2, promo3, promo4, promo5, promo6, promo7, promo8, promo9, promo10, promo11, promo12));
		
		LOG.info("PROMOCAO SALVO: " +promo1);
		LOG.info("PROMOCAO SALVO: " +promo2);
		LOG.info("PROMOCAO SALVO: " +promo3);
		LOG.info("PROMOCAO SALVO: " +promo4);
		LOG.info("PROMOCAO SALVO: " +promo5);
		LOG.info("PROMOCAO SALVO: " +promo6);
		LOG.info("PROMOCAO SALVO: " +promo7);
		LOG.info("PROMOCAO SALVO: " +promo8);
		LOG.info("PROMOCAO SALVO: " +promo9);
		LOG.info("PROMOCAO SALVO: " +promo10);
		LOG.info("PROMOCAO SALVO: " +promo11);
		LOG.info("PROMOCAO SALVO: " +promo12);
		
		return true;
		

	}
}
