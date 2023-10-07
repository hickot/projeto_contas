package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	public Usuario find(String email) throws Exception {
		
		//abrir conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//escrevendo a query SQL que será executado no banco de dados
		String query = "select * from usuario where email = ?";
		
		//executando a query sql
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		//Defina os valores dos parâmetros
        preparedStatement.setString(1, email);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        Usuario usuario = null;
        
        //verificando se algum registro foi obtido do banco
        if (resultSet.next()) {
        	
        	usuario = new Usuario();  //instanciando
        	usuario.setIdUsuario(resultSet.getInt("idusuario"));
        	usuario.setNome(resultSet.getString("nome"));
        	usuario.setEmail(resultSet.getString("email"));
        }
        
        //fechando a conexão
        connection.close();
        
        //retornando o usuário
        return usuario;
	}
	
	/*
	 * Método para consultar 1 usuário no banco de dados
	 * através do e-mail e da senha
	 */
	public Usuario find(String email, String senha) throws Exception {
		
		//abrindo a conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//escrevendo o comando sql executado no banco de dados
		String query = "select * from usuario where email = ? and senha = ?";
		
		//executando a query sql
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		//Defina os valores dos parâmetros
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, senha);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        //criando um objeto usuário
        Usuario usuario = null;
        
        //verificando se algum registro foi obtido do banco
        if (resultSet.next()) {
        	
        	usuario = new Usuario();  //instanciando
        	usuario.setIdUsuario(resultSet.getInt("idusuario"));
        	usuario.setNome(resultSet.getString("nome"));
        	usuario.setEmail(resultSet.getString("email"));
        	
        }
        
        //fechando a conexão
        connection.close();
    
        //retornando o usuário
        return usuario;
	}
}
