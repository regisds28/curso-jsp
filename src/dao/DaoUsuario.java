package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;

import beans.BeanUsuario;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanUsuario usuario) {

		try {

			String sql = "Insert into usuario(login, senha, nome, cep, rua, bairro, "
					+ " cidade, uf, foto, contenttype, documento, contenttypedoc, "
					+ " fotominiatura, ativo, sexo, perfil) "
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getCep());
			insert.setString(5, usuario.getRua());
			insert.setString(6, usuario.getBairro());
			insert.setString(7, usuario.getCidade());
			insert.setString(8, usuario.getUf());
			insert.setString(9, usuario.getFoto());
			insert.setString(10, usuario.getContenttype());
			insert.setString(11, usuario.getDocumento());
			insert.setString(12, usuario.getContenttypedoc());
			insert.setString(13, usuario.getFotoMiniatura());
			insert.setBoolean(14, usuario.isAtivo());
			insert.setString(15, usuario.getSexo());
			insert.setString(16, usuario.getPerfil());
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
	
	/**
	 * método que retorna a pesquisa 
	 */
	public List<BeanUsuario> listar(String pesquisar) throws Exception{
		String sql = "select * from usuario where login <> 'admin' and nome like '%" + pesquisar + "%' ";
		return consultarUsuarios(sql);
	}	

	public List<BeanUsuario> listar() throws Exception {

		String sql = "select * from usuario where login <> 'admin'";
		return consultarUsuarios(sql);
	}

	private List<BeanUsuario> consultarUsuarios(String sql) throws SQLException {
		List<BeanUsuario> listar = new ArrayList<BeanUsuario>();
		PreparedStatement lista = connection.prepareStatement(sql);
		ResultSet resultSet = lista.executeQuery();

		while (resultSet.next()) {
			BeanUsuario beanCursoJsp = new BeanUsuario();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setCep(resultSet.getString("cep"));
			beanCursoJsp.setRua(resultSet.getString("rua"));
			beanCursoJsp.setBairro(resultSet.getString("bairro"));
			beanCursoJsp.setCidade(resultSet.getString("cidade"));
			beanCursoJsp.setUf(resultSet.getString("uf"));
			// beanCursoJsp.setFoto(resultSet.getString("foto"));
			beanCursoJsp.setFotoMiniatura(resultSet.getString("fotoMiniatura"));
			beanCursoJsp.setContenttype(resultSet.getString("contenttype"));
			beanCursoJsp.setDocumento(resultSet.getString("documento"));
			beanCursoJsp.setContenttypedoc(resultSet.getString("contenttypedoc"));
			beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
			beanCursoJsp.setSexo(resultSet.getString("sexo"));
			beanCursoJsp.setPerfil(resultSet.getString("perfil"));

			listar.add(beanCursoJsp);
		}
		
		return listar;
	}

	public void delete(String id) {
		try {
			String sql = "delete from usuario where id = '" + id + "' and login <> 'admin'";
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

	public BeanUsuario consultar(String id) throws Exception {

		String sql = "select * from usuario where id='" + id + "' and login <> 'admin'";
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultSet = select.executeQuery();
		connection.commit();

		if (resultSet.next()) {
			BeanUsuario consulta = new BeanUsuario();
			consulta.setId(resultSet.getLong("id"));
			consulta.setLogin(resultSet.getString("login"));
			consulta.setSenha(resultSet.getString("senha"));
			consulta.setNome(resultSet.getString("nome"));
			consulta.setCep(resultSet.getString("cep"));
			consulta.setRua(resultSet.getString("rua"));
			consulta.setBairro(resultSet.getString("bairro"));
			consulta.setCidade(resultSet.getString("cidade"));
			consulta.setUf(resultSet.getString("uf"));
			consulta.setFoto(resultSet.getString("foto"));
			consulta.setFotoMiniatura(resultSet.getString("fotoMiniatura"));
			consulta.setContenttype(resultSet.getString("contenttype"));
			consulta.setDocumento(resultSet.getString("documento"));
			consulta.setContenttypedoc(resultSet.getString("contenttypedoc"));
			consulta.setAtivo(resultSet.getBoolean("ativo"));
			consulta.setSexo(resultSet.getString("sexo"));
			consulta.setPerfil(resultSet.getString("perfil"));
			
			return consulta;
		}

		return null;
	}

	public boolean validarLogin(String login) throws Exception {

		String sql = "select count(1) as qtd from usuario where login='" + login + "'";
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultSet = select.executeQuery();
		connection.commit();

		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;/*
												 * se houver algum registro(login com mesmo nome), retorna true (1), se
												 * não, false(0)
												 */
		}

		return false;
	}

	public boolean validarLoginUpdate(String login, String id) throws Exception {

		String sql = "select count(1) as qtd from usuario where login='" + login + "' and id <> " + id;
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultSet = select.executeQuery();
		connection.commit();

		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;/*
												 * se houver algum registro(login com mesmo nome), retorna true (1), se
												 * não, false(0)
												 */
		}

		return false;
	}

	public void atualizar(BeanUsuario usuario) {
		try {
			StringBuilder sql = new StringBuilder();

			sql.append(" update usuario set login = ?, senha = ?, nome = ? ");
			sql.append(", cep = ?, rua = ?, bairro = ?, cidade = ?, uf = ?, ativo = ?, sexo = ?, perfil = ? ");

			if (usuario.isAtualizaImg()) {
				sql.append(", foto = ?, contenttype = ? ");
			}

			if (usuario.isAtualizaPdf()) {
				sql.append(", documento = ?, contenttypedoc = ? ");
			}

			if (usuario.isAtualizaImg()) {
				sql.append(", fotoMiniatura = ? ");
			}

			sql.append(" where id = " + usuario.getId());

			PreparedStatement atualizar = connection.prepareStatement(sql.toString());
			atualizar.setString(1, usuario.getLogin());
			atualizar.setString(2, usuario.getSenha());
			atualizar.setString(3, usuario.getNome());
			atualizar.setString(4, usuario.getCep());
			atualizar.setString(5, usuario.getRua());
			atualizar.setString(6, usuario.getBairro());
			atualizar.setString(7, usuario.getCidade());
			atualizar.setString(8, usuario.getUf());
			atualizar.setBoolean(9, usuario.isAtivo());
			atualizar.setString(10, usuario.getSexo());
			atualizar.setString(11, usuario.getPerfil());

			if (usuario.isAtualizaImg()) {
				atualizar.setString(12, usuario.getFoto());
				atualizar.setString(13, usuario.getContenttype());
			}

			if (usuario.isAtualizaPdf()) {

				if (usuario.isAtualizaPdf() && !usuario.isAtualizaImg()) {
					atualizar.setString(12, usuario.getDocumento());
					atualizar.setString(13, usuario.getContenttypedoc());
				} else {
					atualizar.setString(14, usuario.getDocumento());
					atualizar.setString(15, usuario.getContenttypedoc());
				}
			} else {
				if (usuario.isAtualizaImg()) {
					atualizar.setString(14, usuario.getFotoMiniatura());
				}

			}

			if (usuario.isAtualizaImg() && usuario.isAtualizaPdf()) {
				atualizar.setString(16, usuario.getFotoMiniatura());
			}

			atualizar.executeUpdate();
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
