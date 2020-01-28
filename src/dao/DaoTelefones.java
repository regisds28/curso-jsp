package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanTelefone;
import connection.SingleConnection;

public class DaoTelefones {

	private Connection connection;

	public DaoTelefones() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanTelefone telefone) {

		try {

			String sql = "Insert into telefones (numero, tipo, usuario) values (?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<BeanTelefone> listar(Long user) throws Exception {

		List<BeanTelefone> listar = new ArrayList<BeanTelefone>();

		String sql = "select * from telefones where usuario = " + user;
		PreparedStatement lista = connection.prepareStatement(sql);
		ResultSet resultSet = lista.executeQuery();

		while (resultSet.next()) {
			BeanTelefone telefone = new BeanTelefone();
			telefone.setId(resultSet.getLong("id"));
			telefone.setNumero(resultSet.getString("numero"));
			telefone.setTipo(resultSet.getString("tipo"));
			telefone.setUsuario(resultSet.getLong("usuario"));

			listar.add(telefone);
		}

		return listar;
	}

	public void delete(String id) {
		try {
			String sql = "delete from telefones where id = '" + id + "'";
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
