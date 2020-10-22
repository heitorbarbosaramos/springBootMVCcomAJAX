package com.heitor.springBootComAjax.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
public class Promocao implements Serializable, Comparable<Promocao>{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String titulo;
	@Column(nullable = false)
	private String linkPromocao;
	@Column(nullable = false)
	private String site;
	private String descricao;
	@Column(nullable = false)
	private String linkImagem;
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false)
	private BigDecimal preco;
	private int likes;
	@Column(nullable = false)
	private LocalDate dataCadastro;
	
	@ManyToOne
	@JoinColumn(name = "categoria_fk")
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
	public String getLinkPromocao() {
		return linkPromocao;
	}
	public void setLinkPromocao(String linkPromocao) {
		this.linkPromocao = linkPromocao;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
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
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Promocao [id=");
		builder.append(id);
		builder.append(", titulo=");
		builder.append(titulo);
		builder.append(", linkPromocao=");
		builder.append(linkPromocao);
		builder.append(", site=");
		builder.append(site);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append(", linkImagem=");
		builder.append(linkImagem);
		builder.append(", preco=");
		builder.append(preco);
		builder.append(", likes=");
		builder.append(likes);
		builder.append(", dataCadastro=");
		builder.append(dataCadastro);
		builder.append(", categoria=");
		builder.append(categoria);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int compareTo(Promocao outro) {
		return dataCadastro.compareTo(outro.getDataCadastro());
	}
	
	
	
}
