package br.edu.ifsp.encurtador.model.dao;

import java.util.Map;

import br.edu.ifsp.encurtador.model.entity.Acess;
import br.edu.ifsp.encurtador.model.entity.Link;

public class AcessDaoDatabase implements AcessDao {

	private static final String INSERT = "INSERT INTO tb_acess(link,endereco_ip) VALUES (?,?)";
	private static final String SELECT_ALL = "SELECT endereco_ip, COUNT(endereco_ip) FROM tb_acess WHERE link = ? GROUP BY endereco_ip";
	
	@Override
	public boolean create(Acess acesso) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<Acess,Integer> retrieve(Link link) {
		// TODO Auto-generated method stub
		return null;
	}

}
