package br.edu.ifsp.encurtador.model.dao;

public class AccessDaoFactory {
	public AccessDao factory() {
		return new AccessDaoDatabase();
	}
}
