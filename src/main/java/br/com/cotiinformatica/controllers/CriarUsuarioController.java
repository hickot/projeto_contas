package br.com.cotiinformatica.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class CriarUsuarioController {
	
	//mapeamento da rota da página de criação de usuário
	@RequestMapping(value = "/criar-usuario")
	public ModelAndView criarUsuario() {
		
		//difinindo qual página será aberta no navegador:
		//WEB-INF/views/criar-usuario.jsp
		ModelAndView modelAndView = new ModelAndView("criar-usuario");
		
		return modelAndView;
	}
	
	//método para receber o SUBMIT POST do formulário
		@RequestMapping(value = "/criar-usuario-post", method = RequestMethod.POST)
		public ModelAndView criarUsuarioPost(HttpServletRequest request) {
			
			//difinindo qual página será aberta no navegador:
			//WEB-INF/views/criar-usuario.jsp
			ModelAndView modelAndView = new ModelAndView("criar-usuario");
			
			try {
				
				Usuario usuario = new Usuario();
				
				usuario.setNome(request.getParameter("nome"));
				usuario.setEmail(request.getParameter("email"));
				usuario.setSenha(request.getParameter("senha"));
				
				//gravando no banco
				UsuarioRepository usuarioRepository = new UsuarioRepository();
				usuarioRepository.create(usuario);
				
				//enviando mensagem de sucesso para a página
				modelAndView.addObject("mensagem_sucesso", "Usuário cadastrado com sucesso!");
				
			} catch (Exception e) {
				System.err.println("Erro: " + e.getMessage());
				//enviando mensagem de erro para a página
				modelAndView.addObject("mensagem_erro", e.getMessage());
			}
			
			return modelAndView;
		}
}