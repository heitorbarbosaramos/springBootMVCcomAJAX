package com.heitor.springBootComAjax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoAjaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAjaxApplication.class, args);
		Banner.boasVindas();
		Banner.mostraIp();
		Banner.mostraMarca();
	}

}
