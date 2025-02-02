package br.edu.ifsp.encurtador.model.dao;

public class AcessDaoFactory {
	public AcessDao factory() {
		return new AcessDaoDatabase();
	}
}
