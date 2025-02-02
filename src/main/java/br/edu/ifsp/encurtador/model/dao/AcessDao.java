package br.edu.ifsp.encurtador.model.dao;

import java.util.Map;

import br.edu.ifsp.encurtador.model.entity.Acess;
import br.edu.ifsp.encurtador.model.entity.Link;

public interface AcessDao {

	boolean create(Acess acesso);
	
	Map<Acess,Integer> retrieve(Link link);
}
