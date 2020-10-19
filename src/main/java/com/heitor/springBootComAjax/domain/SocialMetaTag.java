package com.heitor.springBootComAjax.domain;

import java.io.Serializable;

public class SocialMetaTag implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String site;
	private String title;
	private String url;
	private String imagem;
	
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SocialMetaTag [site=");
		builder.append(site);
		builder.append(", title=");
		builder.append(title);
		builder.append(", url=");
		builder.append(url);
		builder.append(", imagem=");
		builder.append(imagem);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
