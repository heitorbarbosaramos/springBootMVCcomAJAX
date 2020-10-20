package com.heitor.springBootComAjax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heitor.springBootComAjax.domain.SocialMetaTag;
import com.heitor.springBootComAjax.service.SocialMetaTagService;

@Controller
@RequestMapping(value = "/meta")
public class SocialMetaTagController {

	@Autowired
	private SocialMetaTagService service;
	
	@PostMapping(value = "/info")
	public ResponseEntity<?> getDadosViaUrl(@RequestParam("url") String url){
		SocialMetaTag socialMTag = service.getSocialMetaTagByUrl(url);
		return socialMTag != null ? ResponseEntity.ok(socialMTag) : ResponseEntity.notFound().build();
	}
}
