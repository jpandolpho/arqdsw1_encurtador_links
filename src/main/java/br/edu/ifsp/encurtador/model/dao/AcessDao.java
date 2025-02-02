package br.edu.ifsp.encurtador.model.dao;

import java.util.List;

import br.edu.ifsp.encurtador.model.entity.Acess;
import br.edu.ifsp.encurtador.model.entity.Link;

public interface AcessDao {

	boolean create(Acess acesso);
	
	List<Acess> retrieve(Link link);
}
