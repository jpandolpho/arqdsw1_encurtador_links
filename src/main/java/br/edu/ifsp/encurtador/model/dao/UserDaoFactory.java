package br.edu.ifsp.encurtador.model.dao;

public class UserDaoFactory {
	public UserDao factory() {
		return new UserDaoDatabase();
	}
}
