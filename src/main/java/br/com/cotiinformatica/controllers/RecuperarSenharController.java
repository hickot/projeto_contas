package br.com.cotiinformatica.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecuperarSenharController {
	
	//mapeamento da rota da página de recuperação de senha
	@RequestMapping(value = "/recuperar-senha")
	public ModelAndView recuperarSenha() {
		
		//difinindo qual página será aberta no navegador:
		//WEB-INF/views/recuperar-senha.jsp
		ModelAndView modelAndView = new ModelAndView("recuperar-senha");
		
		return modelAndView;
	}
}