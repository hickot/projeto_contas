package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class ContaRepository {
	
	public void create(Conta conta)  throws Exception {

		//abrir conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//escrevendo a query SQL que será executado no banco de dados
		String query = "insert INTO conta(nome, data, valor, tipo, descricao, idUsuario) VALUES(?,?,?,?,?,?)";
		
		//executando a query sql
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		//Defina os valores dos parâmetros
        preparedStatement.setString(1, conta.getNome());
        preparedStatement.setDate(2, new java.sql.Date(conta.getData().getTime()));
        preparedStatement.setDouble(3, conta.getValor());
        preparedStatement.setInt(4, conta.getTipo());
        preparedStatement.setString(5, conta.getDescricao());
        preparedStatement.setInt(6, conta.getIdUsuario());
        
        //Execute a inserção
        preparedStatement.execute();
        
        //fechando a conexão com o banco de dados
        connection.close();
	}
	
	public void update(Conta conta)  throws Exception {

		//abrir conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//escrevendo a query SQL que será executado no banco de dados
		String query = "update conta set nome=?, date=?, valor=?, tipo=?, descricao=? where idconta=?";
		
		//executando a query sql
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		//Defina os valores dos parâmetros
        preparedStatement.setString(1, conta.getNome());
        preparedStatement.setDate(2, new java.sql.Date(conta.getData().getTime()));
        preparedStatement.setDouble(3, conta.getValor());
        preparedStatement.setInt(4, conta.getTipo());
        preparedStatement.setString(5, conta.getDescricao());
        preparedStatement.setInt(6, conta.getIdConta());
        
        //Execute a inserção
        preparedStatement.execute();
        
        //fechando a conexão com o banco de dados
        connection.close();
	}
	
	public void delete(Conta conta)  throws Exception {
		
		//abrir conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//escrevendo a query SQL que será executado no banco de dados
		String query = "delete from conta where idconta=?";
		
		//executando a query sql
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		//Defina os valores dos parâmetros
		preparedStatement.setInt(1, conta.getIdConta());
		
		//Execute a inserção
        preparedStatement.execute();
        
        //fechando a conexão com o banco de dados
        connection.close();
	}

	public List<Conta> findByDatas(Date dataMin, Date dataMax, Integer idUsuario)  throws Exception {
		
		//abrir conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//escrevendo a query SQL que será executado no banco de dados
		String query = "select * from conta "
				+ "where data between ? and ? and idusuario=? "
				+ "order by data desc";
		
		//executando a query sql
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		//Defina os valores dos parâmetros
		preparedStatement.setDate(1, new java.sql.Date(dataMin.getTime()));
		preparedStatement.setDate(2, new java.sql.Date(dataMax.getTime()));
		preparedStatement.setInt(3, idUsuario);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		List<Conta> lista = new ArrayList<Conta>();
		
		while(resultSet.next()) {
			
			Conta conta = new Conta();
			
			conta.setIdConta(resultSet.getInt("idconta"));
			conta.setNome(resultSet.getString("nome"));
			conta.setData(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("data")));
			conta.setValor(resultSet.getDouble("valor"));
			conta.setTipo(resultSet.getInt("tipo"));
			conta.setDescricao(resultSet.getString("descricao"));
			conta.setIdUsuario(resultSet.getInt("idusuario"));
			
			lista.add(conta);
		}
		
		//fechando a conexão com o banco de dados
        connection.close();
		
		return lista;
	}
	
	public Conta findById(Integer idconta)  throws Exception {

		//abrir conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//escrevendo a query SQL que será executado no banco de dados
		String query = "select * from conta where idconta=?";
		
		//executando a query sql
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		//Defina os valores dos parâmetros
		preparedStatement.setInt(1, idconta);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		Conta conta = null;
		
		//verificando se algum registro foi obtido do banco
        if (resultSet.next()) {
        	
        	conta = new Conta();
        	conta.setIdConta(resultSet.getInt("idconta"));
			conta.setNome(resultSet.getString("nome"));
			conta.setData(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("data")));
			conta.setValor(resultSet.getDouble("valor"));
			conta.setTipo(resultSet.getInt("tipo"));
			conta.setDescricao(resultSet.getString("descricao"));
			conta.setIdUsuario(resultSet.getInt("idusuario"));
        }
        
        //fechando a conexão
        connection.close();
        
        //retornando o conta
		return conta;
	}
}
