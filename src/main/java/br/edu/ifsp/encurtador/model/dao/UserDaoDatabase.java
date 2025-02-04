package br.edu.ifsp.encurtador.model.dao;

import java.sql.SQLException;

import br.edu.ifsp.encurtador.model.dao.connection.DatabaseConnection;
import br.edu.ifsp.encurtador.model.entity.User;

class UserDaoDatabase implements UserDao {

	private static final String INSERT = "INSERT INTO tb_user(login,senha) VALUES (?,?)";
	private static final String SELECT_BY_LOGIN = "SELECT * FROM tb_user WHERE login = ?";
	
	@Override
	public boolean insert(User user) {
		int rows = 0;
		if(user!=null) {
			try(var connection = DatabaseConnection.getConnection();
				var statement = connection.prepareStatement(INSERT)){
				
				statement.setString(1, user.getLogin());
				statement.setString(2, user.getSenha());
				
				rows = statement.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public User findByLogin(String login) {
		User user = null;
		try(var connection = DatabaseConnection.getConnection();
			var statement = connection.prepareStatement(SELECT_BY_LOGIN)){
			
			statement.setString(1, login);
			var resultSet = statement.executeQuery();
			if(resultSet.next()) {
				user = new User(resultSet.getString("login"),resultSet.getString("senha"),true);
				
				LinkDao dao = new LinkDaoFactory().factory();
				dao.retrieve(user);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
