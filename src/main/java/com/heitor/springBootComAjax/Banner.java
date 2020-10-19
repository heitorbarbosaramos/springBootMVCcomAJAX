package com.heitor.springBootComAjax;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Banner {
	
	public static void boasVindas() {
		System.out.println("=====================================================================================");
		System.out.println("   _/          _/  _/_/_/_/  _/         _/_/_/    _/_/     _/      _/  _/_/_/_/ ");  
		System.out.println("  _/          _/  _/        _/       _/        _/    _/   _/_/  _/_/  _/        ");  
		System.out.println(" _/    _/    _/  _/_/_/    _/       _/        _/    _/   _/  _/  _/  _/_/_/     ");  
		System.out.println("  _/  _/  _/    _/        _/       _/        _/    _/   _/      _/  _/          ");  
		System.out.println("   _/  _/      _/_/_/_/  _/_/_/_/   _/_/_/    _/_/     _/      _/  _/_/_/_/     ");
		System.out.println("=====================================================================================");
	}

	public static void BannerFimOperacao() {
		System.out.println("                                                                            \\|/     ");
		System.out.println("                                                                           (* *)     ");
		System.out.println("                                                                       ooO--(_)--Ooo-");
		System.out.println("=====================================================================================");

	}

	public static void BannerSucesso() {
		System.out.println("              ___     ");
		System.out.println("             / \\`*   ");
		System.out.println("            (o o)     ");
		System.out.println("        ooO--(_)--Ooo-");
		System.out.println("=====================================================================================");
	}
	
	
	public static void mostraIp() {

		try {

			String ipDaMaquina = InetAddress.getLocalHost().getHostAddress();
			String nomeDaMaquina = InetAddress.getLocalHost().getHostName();

			System.out.println("======================================================================================");
			System.out.println("||     |            ||                                                              ||");
			System.out.println("||     |.===.       ||    ENDERECO SISTEMA: http://" + ipDaMaquina + ":8080/              ||");
			System.out.println("||     {}o o{}      ||    NOME DA MAQUINA: " + nomeDaMaquina + "                          ||");
			System.out.println("||  ooO--(_)--Ooo-  ||                                                              ||");
			System.out.println("======================================================================================");
		} catch (UnknownHostException e) {
			System.out.println("Não foi Possivel Pegar o Endereço da Maquina.");
			e.printStackTrace();
		}
	}
	
	public static void mostraMarca() {
		System.out.println("======================================================================ooO=========Ooo=");
		System.out.println("                                   OPERANDO                            \\\\  (o o)  //");
		System.out.println("============================================================================(_)=======");
		System.out.println("||         _/_/_/    _/_/_/      _/_/_/      _/          _/     _/_/_/_/ _/_/_/     ||");
		System.out.println("||      _/            _/      _/             _/          _/    _/       _/    _/    ||");
		System.out.println("||       _/_/        _/        _/_/          _/    _/    _/   _/_/_/   _/_/_/       ||");
		System.out.println("||          _/      _/            _/         _/  _/  _/  _/  _/       _/    _/      ||");
		System.out.println("||   _/_/_/      _/_/_/    _/_/_/            _/  _/   _/ _/ _/_/_/_/ _/_/_/         ||");
		System.out.println("======================================================================================");
	}
	
	public static void site() {
		System.out.println("                                                                               SITE");
		System.out.println("======================================================================================");
	}
	
	public static void ambienteProducao() {
		System.out.println("=====================================================================================");
		System.out.println("       |-|      +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+");
		System.out.println("      _|_|_     |A| |M| |B| |I| |E| |N| |T| |E|   |P| |R| |O| |D| |U| |C| |A| |O|");
		System.out.println("      (o o)     +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+");
		System.out.println("  ooO--(_)--Ooo"); 
		System.out.println("=====================================================================================");
	}
	
	public static void ambienteTeste() {
		System.out.println("=====================================================================================");
		System.out.println(" #              +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+   +-+ +-+ +-+ +-+ +-+");        
		System.out.println(" #  <_*_>       |A| |M| |B| |I| |E| |N| |T| |E|   |D| |E|   |T| |E| |S| |T| |E|");
		System.out.println(" #  (o o)       +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+   +-+ +-+ +-+ +-+ +-+");
		System.out.println(" 8---(_)--Ooo   ");
		System.out.println("=====================================================================================");
	}
	
	public static void ambienteDesenvolvimento() {
		System.out.println("=====================================================================================");
		System.out.println(" #                 # +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+   +-+ +-+ +-+");        
		System.out.println(" #=ooO=========Ooo=# |A| |M| |B| |I| |E| |N| |T| |E|   |D| |E|   |D| |E| |V|");
		System.out.println(" #      (- -)      # +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+   +-+ +-+ +-+");
		System.out.println("  -------(_)------");
		System.out.println("=====================================================================================");
	}
}
