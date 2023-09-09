package br.com.cotiinformatica.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AutenticarController {
	
	//definindo a tora para abrir a página no navegador
	@RequestMapping(value = "/") //página raiz (inicial) do projeto
	public ModelAndView autenticar() {
		
		//difinindo qual página será aberta no navegador:
		//WEB-INF/views/autenticar.jsp
		ModelAndView modelAndView = new ModelAndView("autenticar");
		
		return modelAndView;
	}
}