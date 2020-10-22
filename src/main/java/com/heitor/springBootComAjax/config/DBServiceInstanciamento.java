package com.heitor.springBootComAjax.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heitor.springBootComAjax.domain.Categoria;
import com.heitor.springBootComAjax.repository.CategoriaRepository;

@Service
public class DBServiceInstanciamento {
	
	@Autowired
	private CategoriaRepository catService;
	
	
	public void instantiationDataBasa() {
		
		Categoria cat1 = new Categoria(); cat1.setId(null); cat1.setTitulo("Informática");
		Categoria cat2 = new Categoria(); cat2.setId(null); cat2.setTitulo("Cursos");
		Categoria cat3 = new Categoria(); cat3.setId(null); cat3.setTitulo("Eletrodoméstico");
		Categoria cat4 = new Categoria(); cat4.setId(null); cat4.setTitulo("Eletronicos");
		Categoria cat5 = new Categoria(); cat5.setId(null); cat5.setTitulo("Livros");
		Categoria cat6 = new Categoria(); cat6.setId(null); cat6.setTitulo("Movéis");
		Categoria cat7 = new Categoria(); cat7.setTitulo("Cama, mesa e banho");
		
		catService.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
	}

}
