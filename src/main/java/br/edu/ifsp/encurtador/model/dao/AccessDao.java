package br.edu.ifsp.encurtador.model.dao;

import br.edu.ifsp.encurtador.model.entity.Access;
import br.edu.ifsp.encurtador.model.entity.Link;

public interface AccessDao {

	boolean create(Access acesso);
	
	void retrieve(Link link);
}
