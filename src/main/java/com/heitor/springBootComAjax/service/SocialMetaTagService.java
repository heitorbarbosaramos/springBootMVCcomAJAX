package com.heitor.springBootComAjax.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.heitor.springBootComAjax.domain.SocialMetaTag;

@Service
public class SocialMetaTagService {
	
	private Logger LOG = LoggerFactory.getLogger("ComandLineRunner");
	
	public SocialMetaTag getSocialMetaTagByUrl(String url) {
		
		SocialMetaTag twitter = getTwitterCardByUrl(url);
		if(isEmpty(twitter) == false) {
			LOG.info("SOCIAL META TAG TWITTER");
			LOG.info(twitter.toString());
			return twitter;
		}
		
		SocialMetaTag og = getOpenGraphByUrl(url);
		if(isEmpty(og)  == false) {
			LOG.info("SOCIAL META TAG OPEN GRAPH");
			LOG.info(og.toString());
			return og;
		}

		return null;
	}

	private SocialMetaTag getOpenGraphByUrl(String url) {
		SocialMetaTag tag = new SocialMetaTag();
		try {
			
			Document doc = Jsoup.connect(url).get();
			tag.setTitle(doc.head().select("meta[property=og:title]").attr("content"));
			tag.setSite(doc.head().select("meta[property=og:site_name]").attr("content"));
			tag.setImagem(doc.head().select("meta[property=og:image]").attr("content"));
			tag.setUrl(doc.head().select("meta[property=og:url]").attr("content"));
			
		} catch (IOException e) {
			LOG.error(e.getMessage(), e.getCause());
		}
		return tag;
	}
	
	private SocialMetaTag getTwitterCardByUrl(String url) {
		SocialMetaTag tag = new SocialMetaTag();
		try {
			
			Document doc = Jsoup.connect(url).get();
			tag.setTitle(doc.head().select("meta[name=twitter:title]").attr("content"));
			tag.setSite(doc.head().select("meta[name=twitter:site]").attr("content"));
			tag.setImagem(doc.head().select("meta[name=twitter:image]").attr("content"));
			tag.setUrl(doc.head().select("meta[name=twitter:url]").attr("content"));
			
		} catch (IOException e) {
			LOG.error(e.getMessage(), e.getCause());
		}
		return tag;
	}
	
	private boolean isEmpty(SocialMetaTag tag) {
		if(tag.getImagem().isEmpty()) return true;
		if(tag.getTitle().isEmpty()) return true;
		if(tag.getUrl().isEmpty()) return true;
		return false;
	}
}
