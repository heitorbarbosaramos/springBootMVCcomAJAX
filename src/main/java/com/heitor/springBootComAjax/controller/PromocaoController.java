package com.heitor.springBootComAjax.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heitor.springBootComAjax.domain.Categoria;
import com.heitor.springBootComAjax.domain.Promocao;
import com.heitor.springBootComAjax.repository.CategoriaRepository;
import com.heitor.springBootComAjax.repository.PromocaoRepository;

@Controller
@RequestMapping(value = "/promocao")
public class PromocaoController {

	private Logger LOG = LoggerFactory.getLogger(PromocaoController.class);
	@Autowired
	private PromocaoRepository repoPromo;
	
	@Autowired
	private CategoriaRepository repoCategoria;
	
	@ModelAttribute("categorias")
	public List<Categoria> getCategorias(){
		return repoCategoria.findAll();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String abrirCadastro() {
		return "promo-add";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> savePromocao(Promocao promocao){
		promocao.setDataCadastro(LocalDate.now());
		LOG.info("Promocao {} " + promocao);
		repoPromo.save(promocao);
		return ResponseEntity.ok().build();
	}
}
