package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class UsuarioRepository {
	
	public void create(Usuario usuario) throws Exception {
		
		//abrir conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//escrevendo a query SQL que será executado no banco de dados
		String query = "insert INTO usuario(nome, email, senha) VALUES(?,?,?)";
		
		//executando a query sql
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		//Defina os valores dos parâmetros
        preparedStatement.setString(1, usuario.getNome());
        preparedStatement.setString(2, usuario.getEmail());
        preparedStatement.setString(3, usuario.getSenha());
        
        //Execute a inserção
        preparedStatement.execute();
        
        //fechando a conexão com o banco de dados
        connection.close();
	}

}
