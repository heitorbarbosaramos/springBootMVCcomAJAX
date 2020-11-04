package com.heitor.springBootComAjax.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.heitor.springBootComAjax.domain.Categoria;
import com.heitor.springBootComAjax.domain.Promocao;
import com.heitor.springBootComAjax.dto.PromocaoDTO;
import com.heitor.springBootComAjax.repository.CategoriaRepository;
import com.heitor.springBootComAjax.repository.PromocaoRepository;
import com.heitor.springBootComAjax.service.PromocaoDataTablesService;

@Controller
@RequestMapping(value = "/promocao")
public class PromocaoController {

	private Logger LOG = LoggerFactory.getLogger(PromocaoController.class);
	@Autowired
	private PromocaoRepository repoPromo;

	@Autowired
	private CategoriaRepository repoCategoria;

	@GetMapping("/tabela")
	public String showTable() {
		return "promo-datatables";
	}
	
	@GetMapping("/datatables/server")
	public ResponseEntity<?> datatable(HttpServletRequest request){
		Map<String, Object> data = new PromocaoDataTablesService().execute(repoPromo, request);
		return ResponseEntity.ok(data);
	}
	
	@ModelAttribute("categorias")
	public List<Categoria> getCategorias() {
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

	@RequestMapping(value = "/like/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> adcionarLikes(@PathVariable(value = "id") Long id) {
		repoPromo.updateSomarLikes(id);
		int likes = repoPromo.findLikesById(id);
		return ResponseEntity.ok(likes);
	}

	@GetMapping("/list/ajax")
	public String listarCard(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "site", defaultValue = "") String site, ModelMap model) {

		Sort sort = Sort.by(Sort.Direction.ASC, "dataCadastro");
		PageRequest pageRequest = PageRequest.of(0, 8, sort);
		
		if (!site.isEmpty()) {
			model.addAttribute("promocoes", repoPromo.findBySitePage(site, pageRequest));
		} else {
			model.addAttribute("promocoes", repoPromo.findAll(pageRequest));			
		}
		return "promo-card";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> savePromocao(@Valid Promocao promocao, BindingResult result) {

		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		promocao.setDataCadastro(LocalDateTime.now());
		LOG.info("Promocao {} " + promocao);
		repoPromo.save(promocao);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/site")
	public ResponseEntity<List<String>> autocompleteByTermo(@RequestParam(value = "termo") String termo) {
		List<String> sites = repoPromo.findBySiteTermo(termo);
		return ResponseEntity.ok(sites);
	}

	@GetMapping("/site/list")
	public String listarPorSite(@RequestParam(value = "site") String site, ModelMap map) {
		Sort sort = Sort.by(Sort.Direction.ASC, "dataCadastro");
		PageRequest pageRequest = PageRequest.of(0, 8, sort);
		map.addAttribute("promocoes", repoPromo.findBySitePage(site, pageRequest));
		return "promo-card";
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<?> excluirPromocao(@PathVariable(value = "id") Long id){
		repoPromo.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/edit/{id}")
	public ResponseEntity<?> preEditarPromocao(@PathVariable(value = "id") Long id){
		Promocao promo = repoPromo.findById(id).get();
		return ResponseEntity.ok(promo);
	}
	
	@GetMapping("/edit")
	public ResponseEntity<?> editarPromocao(@Valid PromocaoDTO dto, BindingResult result){
		
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		Promocao promo = repoPromo.findById(dto.getId()).get();
		promo.setCategoria(dto.getCategoria());
		promo.setDescricao(dto.getDescricao());
		promo.setLinkImagem(dto.getLinkImagem());
		promo.setPreco(dto.getPreco());
		promo.setTitulo(dto.getTitulo());
		
		if(repoPromo.save(promo) == null) {
			return ResponseEntity.unprocessableEntity().build();
		};
		
		return ResponseEntity.ok().build();
	}
}
