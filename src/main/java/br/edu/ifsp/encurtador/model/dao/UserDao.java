package br.edu.ifsp.encurtador.model.dao;

import br.edu.ifsp.encurtador.model.entity.User;

public interface UserDao {
	
	boolean insert(User user);
	
	User findByLogin(String login);
}
