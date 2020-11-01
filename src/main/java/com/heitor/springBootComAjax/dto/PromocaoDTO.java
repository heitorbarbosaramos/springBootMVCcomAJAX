package com.heitor.springBootComAjax.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.heitor.springBootComAjax.domain.Categoria;
import com.sun.istack.NotNull;

public class PromocaoDTO {

	@NotNull
	private Long id;
	
	@NotBlank(message = "Um título é requerido")
	private String titulo;
	
	private String descricao;
	
	@Column(nullable = false)
	private String linkImagem;
	
	@NotBlank(message = "Um valor é requerido")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal preco;
	
	@NotBlank(message = "Uma categoria é requirido")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLinkImagem() {
		return linkImagem;
	}

	public void setLinkImagem(String linkImagem) {
		this.linkImagem = linkImagem;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
