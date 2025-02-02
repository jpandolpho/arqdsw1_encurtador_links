package br.edu.ifsp.encurtador.model.dao;

import br.edu.ifsp.encurtador.model.entity.User;

public class UserDaoDatabase implements UserDao {

	private static final String INSERT = "INSERT INTO tb_user(login,senha) VALUES (?,?)";
	private static final String SELECT_BY_LOGIN = "SELECT * FROM tb_user WHERE login = ?";
	
	@Override
	public boolean insert(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

}
