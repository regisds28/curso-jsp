package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCategoria;
import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProdutos {

	private Connection connection;

	public DaoProdutos() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanProduto beansProdutos) {
		try {

			String sql = "INSERT into produtos (nome, descricao, valor, quantidade, categoria_id) values (?,?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, beansProdutos.getNome());
			insert.setString(2, beansProdutos.getDescricao());
			insert.setDouble(3, beansProdutos.getValor());
			insert.setDouble(4, beansProdutos.getQuantidade());
			insert.setLong(5, beansProdutos.getCategoria_id());
			insert.execute();
			connection.commit();

			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BeanProduto> listar() throws Exception {

		List<BeanProduto> listar = new ArrayList<BeanProduto>();

		String sql = "select * from produtos";
		PreparedStatement lista = connection.prepareStatement(sql);
		ResultSet resultSet = lista.executeQuery();

		while (resultSet.next()) {
			BeanProduto beansProdutos = new BeanProduto();
			beansProdutos.setId(resultSet.getLong("id"));
			beansProdutos.setNome(resultSet.getString("nome"));
			beansProdutos.setDescricao(resultSet.getString("descricao"));
			beansProdutos.setValor(resultSet.getDouble("valor"));
			beansProdutos.setQuantidade(resultSet.getDouble("quantidade"));

			listar.add(beansProdutos);

		}

		return listar;
	}
	
	public List<BeanCategoria> listarCategoria() throws Exception {

		List<BeanCategoria> listar = new ArrayList<BeanCategoria>();

		String sql = "select * from categoria";
		PreparedStatement lista = connection.prepareStatement(sql);
		ResultSet resultSet = lista.executeQuery();

		while (resultSet.next()) {
			BeanCategoria beanCategoria = new BeanCategoria();
			beanCategoria.setId(resultSet.getLong("id"));
			beanCategoria.setNome(resultSet.getString("nome"));

			listar.add(beanCategoria);

		}

		return listar;
	}

	public void delete(String id) throws SQLException {

		String sql = "DELETE from produtos where id = " + id;
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.execute();
		connection.commit();

		try {
			connection.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(BeanProduto produtos) {
		try {
			String sql = "update produtos set nome = ?, descricao = ?, valor = ?, quantidade = ?, categoria_id = ? where id = "
					+ produtos.getId();
			PreparedStatement update = connection.prepareStatement(sql);

			update.setString(1, produtos.getNome());
			update.setString(2, produtos.getDescricao());
			update.setDouble(3, produtos.getValor());
			update.setDouble(4, produtos.getQuantidade());
			update.setLong(5, produtos.getCategoria_id());
			update.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
			}

		}
	}

	public BeanProduto consultar(String id) throws Exception {

		String sql = "select * from produtos where id='" + id + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		connection.commit();

		if (resultSet.next()) {
			BeanProduto consulta = new BeanProduto();
			consulta.setId(resultSet.getLong("id"));
			consulta.setNome(resultSet.getString("nome"));
			consulta.setDescricao(resultSet.getString("descricao"));
			consulta.setValor(resultSet.getDouble("valor"));
			consulta.setQuantidade(resultSet.getDouble("quantidade"));
			consulta.setCategoria_id(resultSet.getLong("categoria_id"));

			return consulta;
		}

		return null;
	}

}
