package com.heitor.springBootComAjax.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/list")
	public String listarOferta(ModelMap model) {
		Sort sort = Sort.by(Direction.ASC, "dataCadastro");
		PageRequest pageRequest = PageRequest.of(0, 8, sort);
		model.addAttribute("promocoes", repoPromo.findAll(pageRequest));
		return "promo-list";
	}
	
	@GetMapping("/list/ajax")
	public String listarCard(@RequestParam(name = "page", defaultValue = "1") int page, ModelMap model) {
		Sort sort = Sort.by(Direction.ASC, "dataCadastro");
		PageRequest pageRequest = PageRequest.of(page, 8, sort);
		model.addAttribute("promocoes", repoPromo.findAll(pageRequest));
		return "promo-card";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> savePromocao(@Valid Promocao promocao, BindingResult result){
		
		if(result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for(FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		
		promocao.setDataCadastro(LocalDate.now());
		LOG.info("Promocao {} " + promocao);
		repoPromo.save(promocao);
		return ResponseEntity.ok().build();
	}
}
