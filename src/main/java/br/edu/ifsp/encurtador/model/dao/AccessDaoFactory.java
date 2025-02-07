package br.edu.ifsp.encurtador.model.dao;

public class AccessDaoFactory {
	public static AccessDao factory() {
		return new AccessDaoDatabase();
	}
}
