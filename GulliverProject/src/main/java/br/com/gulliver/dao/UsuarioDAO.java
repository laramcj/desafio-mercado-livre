package br.com.gulliver.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.gulliver.beans.Usuario;

public class UsuarioDAO implements DAO<Usuario> {
	private DataSource dataSource;
	
	public UsuarioDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public DataSource getDataSource() {
		return this.dataSource;
	}
	
	@Override
	public void create(Usuario object) {
		try {
			String SQL = "inser into USUARIO " +
			"(id_usuario, nome_usuario, email, senha, nome, sobrenome, endereco, "
			+ "cidade,  pais, cep, descricao) " + "values(?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ "?, ?)";
			
			PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
			
			stm.setInt(1, object.getId_usuario());
			stm.setString(2, object.getNome_usuario());
			stm.setString(3, object.getEmail());
			stm.setString(4, object.getSenha());
			stm.setString(5, object.getNome());
			stm.setString(6, object.getSobrenome());
			stm.setString(7, object.getEndereco());
			stm.setString(8, object.getCidade());
			stm.setString(9, object.getPais());
			stm.setString(10, object.getCep());
			stm.setString(11	, object.getDescricao());
			
			int resultado = stm.executeUpdate();
			if(resultado != 0) {
				System.out.println("Usuário cadastrado com sucesso");
			} else {
				System.out.println("Falha ao cadastrar o usuário");
				throw new RuntimeException("Falha ao cadastrar o usuário");
			}
		}
		catch(Exception ex) {
			System.out.println("Erro no método usuarioDao.create " + ex.getMessage());
		}
	}
	
	@Override
	public Usuario read(Usuario object) {
		try {
			String SQL = "SELECT * FROM USUARIO " +
					"WHERE email=? and senha=?";
			
			PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
			
			stm.setString(1, object.getEmail());
			stm.setString(2, object.getSenha());
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				Usuario resultado = new Usuario();
				resultado.setEmail(rs.getString("Email"));
				resultado.setId_usuario(rs.getInt("Id"));
				resultado.setNome(rs.getString("Nome"));
				resultado.setNome_usuario(rs.getString("Nome_usuario"));
			}
		}
		catch(Exception ex) {
			System.out.println("Erro no método usuarioDao.create " + ex.getMessage());
		}
		return null;
	}
	
	@Override
	public void update(Usuario object) {
	
	}
	
	@Override
	public void delete(Usuario object) {
	
	}
}
