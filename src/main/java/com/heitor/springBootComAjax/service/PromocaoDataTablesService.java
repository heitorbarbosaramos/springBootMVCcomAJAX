package com.heitor.springBootComAjax.service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.heitor.springBootComAjax.domain.Promocao;
import com.heitor.springBootComAjax.repository.PromocaoRepository;

public class PromocaoDataTablesService {
	
	@Autowired
	private PromocaoRepository repoPromocao;

	public String[] cols = {
			"id",
			"titulo",
			"site",
			"linkPromocao",
			"descricao",
			"linkImagem",
			"preco",
			"likes",
			"dataCadastro",
			"categoria"
	};
	
	public Map<String, Object> execute(PromocaoRepository repository, HttpServletRequest request){
		
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		int draw = Integer.parseInt(request.getParameter("draw"));
		int current = currentPage(start, length);
		String column = columnName(request);
		Sort.Direction direction = orderBy(request);
		String search = searchBy(request);
		
		Pageable pageable = PageRequest.of(current, length, direction, column);
		
		Page<Promocao> page = queryBy(search, repository, pageable);
		
		Map<String, Object> json = new LinkedHashMap<>();
		json.put("draw", draw);
		json.put("recordsTotal", page.getTotalElements());
		json.put("recordsFiltered", page.getTotalElements());
		json.put("data", page.getContent());
		
		return json;
	}

	private String searchBy(HttpServletRequest request) {		
		
		return request.getParameter("search[value]").isEmpty()? "" : request.getParameter("search[value]");
	}

	private Page<Promocao> queryBy(String search, PromocaoRepository repository, Pageable pageable) {
		if(search.isEmpty()) {
			return repository.findAll(pageable);
		}
		if(search.matches("^[0-9]+([.,][0-9]{2}?$)")) {
			search = search.replace(",", ".");
			return repoPromocao.findByPreco(new BigDecimal(search), pageable);
		}
		return repository.findByTituloOrSiteOrCategoria(search, pageable);
	}

	private Direction orderBy(HttpServletRequest request) {
		String order = request.getParameter("order[0][dir]") != null ? "ASC" : request.getParameter("order[0][dir]") ;
		return Sort.Direction.valueOf(order);
	}

	private String columnName(HttpServletRequest request) {
		int iCol = Integer.parseInt(request.getParameter("order[0][column]"));
		return cols[iCol];
	}

	private int currentPage(int start, int lenght) {
		//start page	: 0		| 1			| 2			| 3			| ...
		//lenght 		: 0-9	| 10 - 19	| 20 - 29	| 30 - 39	| ...
		return start / lenght;
	}
}
