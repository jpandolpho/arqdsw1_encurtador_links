package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;

import br.edu.ifsp.encurtador.model.dao.LinkDao;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.Link;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EncurtarCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var textLink = request.getParameter("textLink");
		
		LinkDao dao = new LinkDaoFactory().factory();
		Link link = dao.retrieve(textLink);
		
		boolean retrieved = false;
		boolean created = false;
		
		if(link==null) {
			link = new Link(textLink);
			created = dao.create(null, link);
		}else {
			retrieved = true;
		}
		
		if(created || retrieved) {
			request.setAttribute("link", link.getLinkEncurtado());
		}
		
		if(!created && !retrieved) {
			request.setAttribute("msg", "Erro ao criar link curto.");
		}
		
		return "front.do?action=home";
	}

}
